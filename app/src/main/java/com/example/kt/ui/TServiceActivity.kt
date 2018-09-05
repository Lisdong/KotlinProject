package com.example.kt.ui

import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.example.kt.R
import com.example.kt.adapter.ChatOnAnAdapter
import com.example.kt.bean.AnBean
import com.example.kt.bean.ServiceEntity
import com.example.kt.bean.TurLingGson
import com.example.kt.ui.base.BaseActivity
import com.example.kt.ui.presenter.CServiceActivityPresenter
import com.example.kt.ui.view.ICSContract
import com.example.kt.ui.view.ITSContract
import com.example.kt.utils.ToastUtil
import kotlinx.android.synthetic.main.customer_service_layout.*

/**
 * Created By LRD
 * on 2018/9/5  notes：
 */
public class TServiceActivity :BaseActivity(), ICSContract.View{

    private var data = ArrayList<ServiceEntity>()
    private var mChatAdapter : ChatOnAnAdapter = ChatOnAnAdapter(data)
    private val mPresenter = CServiceActivityPresenter(this,this);
    override fun setLayoutResID(): Int {
        return R.layout.t_service_layout
    }

    override fun init() {
        val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        //mLayoutManager.stackFromEnd = true
        chat_rv.layoutManager = mLayoutManager
        mChatAdapter = ChatOnAnAdapter(data)
        chat_rv.adapter = mChatAdapter

        onSendListener()
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
    }

    override fun loadContent(gson: AnBean) {
        mChatAdapter?.addData(ServiceEntity.service(gson))
        chat_rv.scrollToPosition(data.size-1)
    }


    override fun loadError(str: String) {
        ToastUtil.getInstance(this).showToast(str)
    }

}