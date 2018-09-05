package com.example.kt.http.retrofit

import com.example.kt.http.Url
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created By LRD
 * on 2018/8/31  notes：
 */
open class RetrofitUtil private constructor(){
    private var retrofit: Retrofit
    companion object {
        @Volatile
        private var httpManager : RetrofitUtil? = null

        val instance : RetrofitUtil?
            get() {
                if (httpManager == null){
                    synchronized(RetrofitUtil::class.java) {
                        if (httpManager == null) {
                            httpManager = RetrofitUtil()
                        }
                    }
                }
                return httpManager
            }
    }

    init{
        val okHttpClient = OkHttpClient()
        okHttpClient.newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(HttpLoggingInterceptor(RetrofitLogger()))
        retrofit = Retrofit.Builder()
                .baseUrl(Url.TULING_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    open fun getApi(): API {
        return retrofit.create<API>(API::class.java)
    }
}