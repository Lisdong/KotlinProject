package com.example.kt.ui.presenter

import android.content.Context
import android.support.v4.view.ViewPager
import com.example.kt.adapter.HVAdapter
import com.example.kt.ui.model.HomeModel
import com.example.kt.ui.presenter.callback.HomePImpl
import com.example.kt.views.ViewPagerSlide

/**
 * Created By LRD
 * on 2018/8/13  notes：
 */
class HomePresenter(context: Context): HomePImpl {


    private var mHomeModel:HomeModel? = null

    override fun validate(vp:ViewPagerSlide,adapter: HVAdapter) {
        mHomeModel = HomeModel()
        mHomeModel!!.init(vp,adapter)
    }
}