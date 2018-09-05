package com.example.kt.ui.fragment

import android.media.VolumeShaper
import com.example.kt.R
import com.example.kt.ui.CServiceActivity
import com.example.kt.ui.TServiceActivity
import com.example.kt.ui.base.BaseFragment
import kotlinx.android.synthetic.main.f_xing_layout.*

/**
 * Created By LRD
 * on 2018/8/14  notes：
 */
class XingFragment : BaseFragment() {
    override fun setLayout(): Int {
        return R.layout.f_xing_layout
    }

    override fun init() {
        btn_service.setOnClickListener{
            activity.startActivity(CServiceActivity::class.java)
        }
        btn_i.setOnClickListener{
            activity.startActivity(TServiceActivity::class.java)
        }
    }
}