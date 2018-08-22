package com.example.kt.ui

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.net.Uri
import android.os.IBinder
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.FrameLayout
import com.afollestad.materialdialogs.MaterialDialog
import com.example.kt.IWebviewService
import com.example.kt.R
import com.example.kt.R.layout.web_layout
import com.example.kt.service.WebViewService
import com.example.kt.ui.base.BaseActivity
import com.example.kt.utils.ToastUtil
import com.github.lzyzsd.jsbridge.BridgeWebView
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import com.umeng.socialize.media.UMWeb
import kotlinx.android.synthetic.main.web_layout.*

/**
 * Created By LRD
 * on 2018/8/17  notes：H5
 */
class WebViewActivity : BaseActivity(){
    private var mUrl:String? = null
    private var mWebView: BridgeWebView? = null
    private var mIsBind:Boolean = false
    private var mWebService: IWebviewService? = null
    private var mReload  = false

    override fun setLayoutResID(): Int {
        return web_layout
    }

    override fun init() {
        mUrl = intent.getStringExtra("url")
        initView()
        initListener()
    }

    private fun initView() {
        mWebView = BridgeWebView(this)
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT)
        web_Fl.addView(mWebView,params)

        web_progressBar.max = 100
        setWebViewSetting()

        if (!TextUtils.isEmpty(mUrl)){
            mWebView!!.loadUrl(mUrl);
        }else {
            mWebView!!.loadUrl("https://github.com/LRDDYR");
        }
    }

    override fun onStart() {
        super.onStart()
        if (!mIsBind){
            bindService(Intent(this,WebViewService::class.java),mServiceConnection,BIND_AUTO_CREATE)
        }
    }

    val mServiceConnection = object : ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            mIsBind = false
            mWebService = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mWebService = IWebviewService.Stub.asInterface(service)
            mIsBind = true
        }
    }

    private fun initListener() {
        web_back.setOnClickListener{
            finish()
        }
        error_img.setOnClickListener{
            mReload = true
            mWebView?.reload()
        }
        web_share.setOnClickListener{
            val web = UMWeb(mUrl)
            val image = UMImage(this, R.mipmap.ic_launcher)
            web.title = "来自kotlin学习app"
            web.setThumb(image)
            web.description = web_title.text.toString()
            ShareAction(this)
                    .withMedia(web)
                    .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN)
                    .setCallback(object :UMShareListener{
                        override fun onCancel(p0: SHARE_MEDIA?) {
                            ToastUtil.getInstance(this@WebViewActivity).showToast("分享取消啦")

                        }

                        override fun onError(p0: SHARE_MEDIA?, p1: Throwable?) {
                            ToastUtil.getInstance(this@WebViewActivity).showToast("分享失败啦-$p1")
                        }

                        override fun onStart(p0: SHARE_MEDIA?) {
                        }

                        override fun onResult(p0: SHARE_MEDIA?) {
                            ToastUtil.getInstance(this@WebViewActivity).showToast("分享成功啦")
                        }

                    })
                    .open()
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebViewSetting() {
        val settings = mWebView?.settings
        settings?.javaScriptEnabled = true
        settings?.domStorageEnabled = true
        settings?.cacheMode = WebSettings.LOAD_DEFAULT
        settings?.setAppCacheMaxSize(5 * 1024 * 1024)
        settings?.setRenderPriority(WebSettings.RenderPriority.HIGH)
        settings?.cacheMode = WebSettings.LOAD_NO_CACHE
        settings?.setAppCacheEnabled(true)
        settings?.javaScriptCanOpenWindowsAutomatically = true
        settings?.allowFileAccess = true
        settings?.allowFileAccessFromFileURLs = true
        settings?.allowUniversalAccessFromFileURLs = true

        //设置不允许剪切
        mWebView?.setOnLongClickListener { true }

        // 在当前页面打开链接，而不是启动用户手机上安装的浏览器打开
        mWebView?.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                web_progressBar.visibility = View.VISIBLE
                if (!mReload){
                    mReload = false
                    error_mask.visibility = View.GONE
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                val loadsImagesAutomatically = settings?.loadsImagesAutomatically as Boolean
                if (!loadsImagesAutomatically) {
                    settings.loadsImagesAutomatically = true;
                }
                web_progressBar.visibility = View.GONE;
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                error_mask.visibility = View.VISIBLE
            }

            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                error_mask.visibility = View.VISIBLE
            }
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                mWebView?.loadUrl(mUrl)
                return true
            }
        }

        mWebView?.webChromeClient = object : WebChromeClient(){
            override fun onReceivedTitle(view: WebView?, title: String?) {
                web_title.text = title
            }
            // 更新进度条
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                web_progressBar.progress = newProgress
                if (newProgress == 100){

                }
            }
            //使JS alert可以以Android的AlertDiaolog形式弹出
            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                val dialog = MaterialDialog.Builder(this@WebViewActivity)
                        .onPositive { dialog, which ->
                            result?.confirm()
                        }
                if (message != null){
                    dialog.content(message)
                }
                dialog.positiveText("确定")
                dialog.show()
                return true
            }
        }

        mWebView?.setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            val intent = Intent()
            intent.action = "android.intent.action.VIEW"
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (mWebView != null && mWebView!!.canGoBack()){
                mWebView?.goBack()
                return true
            }else{
                finish()
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onStop() {
        super.onStop()
        if (mIsBind!! && mServiceConnection!= null) {
            unbindService(mServiceConnection)
            mIsBind = false
        }
    }

    override fun onDestroy() {
        if (mWebView != null){
            val parent = mWebView?.parent
            if (parent != null){
                (parent as ViewGroup).removeView(mWebView)
            }
            mWebView?.stopLoading()
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView?.settings?.javaScriptEnabled = false
            mWebView?.clearHistory()
            mWebView?.clearView()
            mWebView?.removeAllViews()
            mWebView?.destroy()
        }
        super.onDestroy()
    }
}