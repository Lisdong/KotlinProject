package com.example.kt.ui.presenter.callback

import android.support.v4.view.ViewPager
import com.example.kt.adapter.HVAdapter
import com.example.kt.views.ViewPagerSlide

/**
 * Created By LRD
 * on 2018/8/13  notes：
 */
interface HomePImpl {
    fun validate(vp: ViewPagerSlide, adapter: HVAdapter)
}