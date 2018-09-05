package com.example.kt.http.retrofit

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created By LRD
 * on 2018/8/31  notes：
 */
class RetrofitLogger :HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.d("retrofitInfo", message)
    }
}