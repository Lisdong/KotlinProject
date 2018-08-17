package com.example.kt.ui.base

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.example.kt.R
import com.example.kt.utils.StatusBarUtil

/**
 * Created By LRD
 * on 2018/8/8  notes：父类
 */
abstract class BaseActivity : AppCompatActivity() {

    var activity : BaseActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar()
        setContentView(setLayoutResID())
        this.activity = this
        init()
    }

    //获取布局文件
    abstract fun setLayoutResID(): Int

    //初始化
    abstract fun init()

    //页面跳转
    open fun startActivity(clz: Class<*>) {
        startActivity(Intent(this@BaseActivity, clz))
    }

    //携带数据的页面跳转
    open fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent()
        intent.setClass(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    //含有Bundle通过Class打开编辑界面
    open fun startActivityForResult(cls: Class<*>, bundle: Bundle?,
                               requestCode: Int) {
        val intent = Intent()
        intent.setClass(this, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }

    override fun onDestroy() {
        super.onDestroy()
        activity = null
    }

    //设置状态栏
    private fun setStatusBar() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){//6.0以上
            if (isShowHeight()){//是否显示状态栏高度
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                window.statusBarColor = resources.getColor(R.color.white,null)
            }else{
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                window.statusBarColor = resources.getColor(R.color.white,null)
            }
        }else{//6.0以下
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            if (isShowHeight()){
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }else{
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            }

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            val isMeiZu : Boolean = StatusBarUtil.FlymeSetStatusBarLightMode(window,true)
            val isXiaoMi : Boolean = StatusBarUtil.MIUISetStatusBarLightMode(window,true)
            if (isMeiZu || isXiaoMi){//只有魅族、小米开放了状态栏字体颜色
                if (isShowHeight()){
                    window.statusBarColor = resources.getColor(R.color.white)
                }else{
                    window.statusBarColor = resources.getColor(R.color.transparent)
                }

                val lightMode = StatusBarUtil.StatusBarLightMode(this)
                StatusBarUtil.StatusBarLightMode(this,lightMode)
            }else{
                if (isShowHeight()){
                    window.statusBarColor = resources.getColor(R.color.white)
                }else{
                    window.statusBarColor = resources.getColor(R.color.transparent)
                }
            }
        }
        window.navigationBarColor = resources.getColor(R.color.transparent)
    }

    open fun isShowHeight(): Boolean {
        return true
    }

}