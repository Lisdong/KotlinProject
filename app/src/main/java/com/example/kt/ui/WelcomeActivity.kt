package com.example.kt.ui

import com.example.kt.R.layout.welcome_layout
import com.example.kt.app.Constant
import com.example.kt.ui.base.BaseActivity
import com.example.kt.ui.presenter.WelcomePresenter
import com.example.kt.ui.view.WelcomeView
import com.example.kt.utils.SharedPreferencesUtils
import kotlinx.android.synthetic.main.welcome_layout.*

/**
 * Created By LRD
 * on 2018/8/8  notes：欢迎页Presenter
 */
class WelcomeActivity : BaseActivity(),WelcomeView{
    var mPresenter : WelcomePresenter? = null

    init {
        mPresenter = WelcomePresenter(this,this)
    }

    override fun setLayoutResID(): Int {
        return welcome_layout
    }

    override fun isShowHeight(): Boolean {
        return false
    }

    override fun init() {
        mPresenter?.validate(wc_vp,wc_box,wc_point,wc_btn)
    }

    override fun skipMainActivity() {
        mPresenter?.skipActivity()
    }
}