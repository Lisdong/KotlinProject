package com.example.kt.ui.presenter.callback

import android.app.Activity
import android.support.v4.view.ViewPager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

/**
 * Created By LRD
 * on 2018/8/10  notes：
 */
interface WePImpl {
    //初始化
    fun validate(wc_vp: ViewPager, wc_box: LinearLayout, wc_point: ImageView, wc_btn: Button)

    //跳转页面
    fun skipActivity()
}