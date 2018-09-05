package com.example.kt.kotlin

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created By LRD
 * on 2018/8/31  notes：
 */
interface asdf {
    @FormUrlEncoded
    @POST("http://www.tuling123.com/openapi/api")
    fun getChat (@Field("key") key: String, @Field("info") info: String): Call<ResponseBody>

}
