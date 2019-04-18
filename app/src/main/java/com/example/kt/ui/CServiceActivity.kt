package com.example.kt.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import com.example.kt.R.layout.customer_service_layout
import com.example.kt.adapter.ChatAdapter
import com.example.kt.bean.ServiceEntity
import com.example.kt.bean.TurLingGson
import com.example.kt.ui.base.BaseActivity
import com.example.kt.ui.presenter.CServiceActivityPresenter
import com.example.kt.ui.view.ICSContract
import kotlinx.android.synthetic.main.customer_service_layout.*
import java.util.ArrayList
import android.view.inputmethod.InputMethodManager
import com.example.kt.bean.AnBean
import com.example.kt.utils.DeviceUtils
import com.example.kt.utils.ToastUtil


/**
 * Created By LRD
 * on 2018/8/29  notes：人工客服
 */
public class CServiceActivity :BaseActivity(), ICSContract.View{

    private var data = ArrayList<ServiceEntity>()
    private var mChatAdapter :ChatAdapter? = null
    private val mPresenter = CServiceActivityPresenter(this,this);

    override fun setLayoutResID(): Int {
        return customer_service_layout
    }

    override fun init() {
        val mLayoutManager =LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
//        mLayoutManager.stackFromEnd = true
        chat_rv.layoutManager = mLayoutManager
        mChatAdapter = ChatAdapter(data)
        chat_rv.adapter = mChatAdapter

        onSendListener()
        web_back.setOnClickListener{
            finish()
        }
    }
    override fun onSendListener() {
        btn_send.setOnClickListener{
            //closeKeyboard()
            if (!TextUtils.isEmpty(et_content.text.toString())){
                mChatAdapter?.addData(ServiceEntity.client(et_content.text.toString()))
                mChatAdapter?.notifyItemInserted(data.size-1);
                et_content.setText("")
                mPresenter.chatWithRobot(et_content.text.toString())
            }
        }
    }

    override fun loadContent(turLingGson: TurLingGson) {
        mChatAdapter?.addData(ServiceEntity.service(turLingGson))
        chat_rv.scrollToPosition(data.size-1)
//        et_content.postDelayed({
//            //模拟人工
//
//        },500)
    }
    override fun loadError(str: String) {
        ToastUtil.getInstance(this).showToast(str)
        //mChatAdapter?.addData(ServiceEntity.error(et_content.text.toString()))
    }
    override fun loadContent(gson: AnBean) {
    }

}