package com.example.kt.ui.fragment.model

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.kt.adapter.BAdapter
import com.example.kt.adapter.BrowseBannerAdapter
import com.example.kt.bean.BannerBean
import com.example.kt.ui.fragment.presenter.BrowsePresenter

/**
 * Created By LRD
 * on 2018/8/15  notes：
 */
interface IBrowseData {
    fun getBannerData(listener: BrowsePresenter.IDataListener)

    fun getListData(currentIndex:Int,listener: BrowsePresenter.IListListener)

    fun setBoxData( data: List<BannerBean.DataBean>)

    fun setBannerHeader(bannerData : List<BannerBean.DataBean>):View?

    fun getBannerAdapter():BrowseBannerAdapter?

    fun setIndicator(point:ImageView,bannerData: List<BannerBean.DataBean>)

    fun setAutoPlay()

    fun setTitleLayout(browse_title: LinearLayout, recyclerView: RecyclerView)

}