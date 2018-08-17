package com.example.kt.ui.fragment.view

import android.view.View
import com.example.kt.bean.BannerBean
import com.example.kt.bean.BrowseBean

/**
 * Created By LRD
 * on 2018/8/15  notes：
 */
interface BrowseView {
    fun showData()

    //  展示轮播图
    fun showBanner()

    //获取头部
    fun getBannerHeader():View?

    //获取adapter
    fun getBannerAdapter()

    //设置头部显隐
    fun setTitleShow()
}