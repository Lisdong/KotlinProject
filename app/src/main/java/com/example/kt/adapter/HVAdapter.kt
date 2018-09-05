package com.example.kt.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.kt.ui.base.BaseFragment

/**
 * Created By LRD
 * on 2018/8/14  notes：
 */
class HVAdapter(fm : FragmentManager,private val mFlist:List<BaseFragment>) : FragmentPagerAdapter(fm){

    override fun getCount(): Int {
        return mFlist.size
    }

    override fun getItem(position: Int): Fragment {
        return mFlist[position]
    }
}