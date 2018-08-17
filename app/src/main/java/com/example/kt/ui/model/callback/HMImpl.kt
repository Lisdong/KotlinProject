package com.example.kt.ui.model.callback

import android.support.v4.view.ViewPager
import com.example.kt.adapter.HVAdapter
import com.example.kt.views.ViewPagerSlide

/**
 * Created By LRD
 * on 2018/8/14  notes：
 */
interface HMImpl {
    fun init(vp: ViewPagerSlide, adapter: HVAdapter)
    fun initData()
    fun initListener()
}