package com.example.kt.ui.model.callback

import android.app.Activity
import android.content.Context
import android.support.v4.view.ViewPager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

/**
 * Created By LRD
 * on 2018/8/10  notes：
 */
interface WeMImpl {
    fun initData(context: Activity, wc_vp: ViewPager, wc_box: LinearLayout, wc_point: ImageView, wc_btn: Button)
    fun initView()
    fun initListener()
    fun skipActivity()
}