package com.example.kt.ui.presenter

import android.app.Activity
import android.content.Context
import android.support.v4.view.ViewPager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.kt.ui.model.WelcomeModel
import com.example.kt.ui.presenter.callback.WePImpl
import com.example.kt.ui.view.WelcomeView

/**
 * Created By LRD
 * on 2018/8/10  notes：
 */
class WelcomePresenter(private val context:Activity, val view:WelcomeView) : WePImpl{

    private val mModel:WelcomeModel = WelcomeModel()

    //初始化
    override fun validate(wc_vp: ViewPager, wc_box: LinearLayout, wc_point: ImageView, wc_btn: Button) {
        mModel.initData(context,wc_vp,wc_box,wc_point,wc_btn)
        mModel.initView()
        mModel.initListener()
    }
    //跳转业务方法
    override fun skipActivity() {
        mModel.skipActivity()
    }
}