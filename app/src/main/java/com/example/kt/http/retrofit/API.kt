package com.example.kt.http.retrofit

import com.example.kt.bean.AnBean
import com.example.kt.bean.TurLingGson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created By LRD
 * on 2018/8/31  notes：
 */
public interface API {

    @FormUrlEncoded
    @POST("http://www.tuling123.com/openapi/api")
    fun getChat (@Field("key") key: String, @Field("info") info: String): Call<TurLingGson>

    @FormUrlEncoded
    @POST("http://nlp.xiaoi.com/ask.do")
    fun getChatAn (@Field("appKey") appKey: String,
                   @Field("appSecret") appSecret: String,
                   @Field("userId") userId: String,
                   @Field("type") type: String
    ): Call<AnBean>
}