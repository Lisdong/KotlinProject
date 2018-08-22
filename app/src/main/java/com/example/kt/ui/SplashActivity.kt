package com.example.kt.ui

import android.graphics.Bitmap
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.kt.R
import com.example.kt.R.layout.splash_layout
import com.example.kt.app.Constant
import com.example.kt.bean.SImageBean
import com.example.kt.http.HttpManager
import com.example.kt.http.ImageCallBack
import com.example.kt.http.RequestBeanCallback
import com.example.kt.http.Url
import com.example.kt.ui.base.BaseActivity
import com.example.kt.utils.SharedPreferencesUtils
import com.lzy.okgo.OkGo
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.splash_layout.*
import java.util.*

/**
 * Created By LRD
 * on 2018/8/8  notes：展示页
 */
class SplashActivity : BaseActivity() {

    private var isLoading = true

    override fun setLayoutResID(): Int {
        return splash_layout
    }

    override fun isShowHeight(): Boolean {
        return false
    }

    override fun init() {
        initData()
        sp_img.postDelayed({
            if (isLoading){
                animation()
            }
        },3000)
    }

    private fun initData() {
        val random = Random()
        val index =random.nextInt(10)
        HttpManager.instance?.requestGet(Url.SPLASH_IMAGE+index,SImageBean::class.java,null,object :RequestBeanCallback<SImageBean>{
            override fun onSuccess(bean: SImageBean) {
                getImage(bean.results[index].url)
                isLoading = false
            }

            override fun onError(error: String) {
            }

        })
    }

    private fun getImage(url: String) {
        HttpManager.instance?.downImage(url,object :ImageCallBack{
            override fun onSuccess(bitmap: Bitmap) {
                sp_img.setImageBitmap(bitmap)
                animation()
            }

            override fun onError(string: String) {
            }

        })
    }

    //入场动画
    private fun animation() {
        sp_img.visibility = View.VISIBLE
        val loadAnimation = AnimationUtils.loadAnimation(activity, R.anim.splash_in)
        loadAnimation.fillAfter = true
        sp_img.animation = loadAnimation
        loadAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                val isFirstInApp = SharedPreferencesUtils.getBoolean(this@SplashActivity, Constant.isFirstInApp,true)
                if (isFirstInApp){
                    startActivity(WelcomeActivity::class.java)
                }else{
                    startActivity(HomeActivity::class.java)
                }
                finish()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        OkGo.getInstance().cancelTag(this)
    }
}