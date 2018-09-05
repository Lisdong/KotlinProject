package com.example.kt.ui.presenter

import android.app.Activity
import android.support.v4.app.FragmentManager
import com.example.kt.adapter.HVAdapter
import com.example.kt.ui.base.BaseFragment
import com.example.kt.ui.fragment.BrowseFragment
import com.example.kt.ui.fragment.RocketFragment
import com.example.kt.ui.fragment.SettingFragment
import com.example.kt.ui.fragment.XingFragment
import com.example.kt.ui.presenter.callback.HomePImpl
import com.example.kt.views.ViewPagerSlide

/**
 * Created By LRD
 * on 2018/8/13  notes：
 */
class HomePresenter(context: Activity): HomePImpl {
    val mContext = context
    override fun validate(vp:ViewPagerSlide, fm: FragmentManager) {
        val fragmentList = ArrayList<BaseFragment>()
        fragmentList.add(0, BrowseFragment())
        fragmentList.add(1, XingFragment())
        fragmentList.add(2, RocketFragment())
        fragmentList.add(3, SettingFragment())
        val mAdapter = HVAdapter(fm,fragmentList)
        vp.adapter = mAdapter
        vp.setCurrentItem(0,false)
        vp.offscreenPageLimit = 3
    }
}