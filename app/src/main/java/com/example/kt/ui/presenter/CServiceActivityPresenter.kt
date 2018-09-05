package com.example.kt.ui.presenter

import android.content.Context
import android.text.Editable
import com.example.kt.bean.AnBean
import com.example.kt.bean.TurLingGson
import com.example.kt.http.Url
import com.example.kt.http.retrofit.RetrofitUtil
import com.example.kt.ui.view.ICSContract
import com.example.kt.utils.ToastUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By LRD
 * on 2018/8/31  notes：...
 */
class CServiceActivityPresenter(context: Context,view:ICSContract.View):ICSContract.Presenter{

    val mView = view
    val mContext = context
    override fun chatWithRobot(text: String) {
        RetrofitUtil.instance?.getApi()?.getChat(Url.KEY_TULING,text)?.enqueue(object : Callback<TurLingGson> {
            override fun onFailure(call: Call<TurLingGson>, t: Throwable) {
                mView.loadError("服务器异常:${t.message}")
            }

            override fun onResponse(call: Call<TurLingGson>, response: Response<TurLingGson>) {
                if (response.isSuccessful){
                    mView.loadContent(response.body()!!)
                }else{
                    ToastUtil.getInstance(mContext).showToast("请求出错啦")
                }
            }

        })
    }
    override fun chatWithAn(text: String) {
        RetrofitUtil.instance?.getApi()?.getChatAn(Url.AN_KEY,Url.AN_SECRET,text,"1")?.enqueue(object : Callback<AnBean> {
            override fun onFailure(call: Call<AnBean>, t: Throwable) {
                mView.loadError("服务器异常:${t.message}")
            }

            override fun onResponse(call: Call<AnBean>, response: Response<AnBean>) {
                if (response.isSuccessful){
                    mView.loadContent(response.body()!!)
                }else{
                    ToastUtil.getInstance(mContext).showToast("请求出错啦")
                }
            }
        })
    }
}