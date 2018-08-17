package com.example.kt.ui.model

import com.example.kt.adapter.HVAdapter
import com.example.kt.ui.base.BaseFragment
import com.example.kt.ui.fragment.BrowseFragment
import com.example.kt.ui.fragment.RocketFragment
import com.example.kt.ui.fragment.SettingFragment
import com.example.kt.ui.fragment.XingFragment
import com.example.kt.ui.model.callback.HMImpl
import com.example.kt.views.ViewPagerSlide

/**
 * Created By LRD
 * on 2018/8/13  notes：
 */
class HomeModel : HMImpl {
    override fun init(vp: ViewPagerSlide, adapter: HVAdapter) {
        val fragmentList = ArrayList<BaseFragment>()
        fragmentList.add(0,BrowseFragment())
        fragmentList.add(1,XingFragment())
        fragmentList.add(2,RocketFragment())
        fragmentList.add(3,SettingFragment())
        adapter.setFragment(fragmentList)
        vp.adapter = adapter
        vp.setCurrentItem(0,false)
        vp.offscreenPageLimit = 3
    }

    override fun initData() {

    }

    override fun initListener() {
    }

}