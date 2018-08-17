package com.example.kt.ui

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.kt.R
import com.example.kt.R.layout.splash_layout
import com.example.kt.app.Constant
import com.example.kt.ui.base.BaseActivity
import com.example.kt.utils.SharedPreferencesUtils
import kotlinx.android.synthetic.main.splash_layout.*

/**
 * Created By LRD
 * on 2018/8/8  notes：展示页
 */
class SplashActivity : BaseActivity() {

    override fun setLayoutResID(): Int {
        return splash_layout
    }

    override fun isShowHeight(): Boolean {
        return false
    }

    override fun init() {
        animation()
    }

    //入场动画
    private fun animation() {
        sp_img.visibility = View.VISIBLE
        val loadAnimation = AnimationUtils.loadAnimation(activity, R.anim.splash_in)
        sp_img.animation = loadAnimation
        loadAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                val isFirstInApp = SharedPreferencesUtils.getBoolean(this@SplashActivity, Constant.isFirstInApp,true)
                if (isFirstInApp){
                    SharedPreferencesUtils.saveBoolean(this@SplashActivity, Constant.isFirstInApp,false)
                    startActivity(WelcomeActivity::class.java)
                }else{
                    startActivity(HomeActivity::class.java)
                }
                finish()
            }
        })
    }
}