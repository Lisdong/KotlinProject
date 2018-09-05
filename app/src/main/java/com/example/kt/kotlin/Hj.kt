package com.example.kt.kotlin

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.example.kt.ui.base.BaseFragment

/**
 * Created By LRD
 * on 2018/9/3  notes：
 */
class Hj(fm: FragmentManager, private val fragmentList: List<BaseFragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): BaseFragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}
