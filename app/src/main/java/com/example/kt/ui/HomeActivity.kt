package com.example.kt.ui

import android.content.Intent
import android.view.View
import com.example.kt.R
import com.example.kt.R.layout.home_layout
import com.example.kt.ui.base.BaseActivity
import com.example.kt.ui.presenter.HomePresenter
import com.example.kt.ui.view.HomeImpl
import com.umeng.socialize.UMShareAPI
import kotlinx.android.synthetic.main.home_layout.*

/**
 * Created By LRD
 * on 2018/8/6  notes：
 */
class HomeActivity : BaseActivity(),HomeImpl, View.OnClickListener {


    override fun setLayoutResID(): Int {
        return home_layout
    }

    override fun isShowHeight(): Boolean {
        return false
    }

    override fun init() {

        val mPresenter = HomePresenter(this)
//
        mPresenter.validate(home_Viewpager,supportFragmentManager)
        initView()
    }

    //初始化
    override fun initView() {
        tab_one.setOnClickListener(this)
        tab_two.setOnClickListener(this)
        tab_three.setOnClickListener(this)
        tab_four.setOnClickListener(this)

        home_img.isEnabled = false
        home_text.setTextColor(resources.getColor(R.color.tab_pressed_color))
    }

    //切换选项卡
    override fun onClick(v: View?) {
        when(v){
            tab_one->{
                home_Viewpager.setCurrentItem(0,false)
                home_img.isEnabled = false
                xing_img.isEnabled = true
                rocket_img.isEnabled = true
                setting_img.isEnabled = true
                home_text.setTextColor(resources.getColor(R.color.tab_pressed_color))
                xing_text.setTextColor(resources.getColor(R.color.tab_color))
                rocket_text.setTextColor(resources.getColor(R.color.tab_color))
                setting_text.setTextColor(resources.getColor(R.color.tab_color))
            }tab_two->{
                home_Viewpager.setCurrentItem(1,false)
                home_img.isEnabled = true
                xing_img.isEnabled = false
                rocket_img.isEnabled = true
                setting_img.isEnabled = true
                home_text.setTextColor(resources.getColor(R.color.tab_color))
                xing_text.setTextColor(resources.getColor(R.color.tab_pressed_color))
                rocket_text.setTextColor(resources.getColor(R.color.tab_color))
                setting_text.setTextColor(resources.getColor(R.color.tab_color))
            }tab_three->{
                home_Viewpager.setCurrentItem(2,false)
                home_img.isEnabled = true
                xing_img.isEnabled = true
                rocket_img.isEnabled = false
                setting_img.isEnabled = true
                home_text.setTextColor(resources.getColor(R.color.tab_color))
                xing_text.setTextColor(resources.getColor(R.color.tab_color))
                rocket_text.setTextColor(resources.getColor(R.color.tab_pressed_color))
                setting_text.setTextColor(resources.getColor(R.color.tab_color))
            }tab_four->{
                home_Viewpager.setCurrentItem(3,false)
                home_img.isEnabled = true
                xing_img.isEnabled = true
                rocket_img.isEnabled = true
                setting_img.isEnabled = false
                home_text.setTextColor(resources.getColor(R.color.tab_color))
                xing_text.setTextColor(resources.getColor(R.color.tab_color))
                rocket_text.setTextColor(resources.getColor(R.color.tab_color))
                setting_text.setTextColor(resources.getColor(R.color.tab_pressed_color))
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data)
    }
}