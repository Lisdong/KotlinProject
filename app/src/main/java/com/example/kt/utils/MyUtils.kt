package com.example.kt.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created By LRD
 * on 2018/8/15  notes：
 */
class MyUtils {
    companion object {
        fun isOpenNetwork(context:Context):Boolean{
            val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connManager.activeNetworkInfo
            if (networkInfo != null) {
                if (ConnectivityManager.TYPE_WIFI == networkInfo.type) {
                    //当前为wifi网络
                } else if (ConnectivityManager.TYPE_MOBILE == networkInfo.type) {
                    //当前为mobile网络
                }
                return connManager.activeNetworkInfo.isAvailable
            }
            return false
        }
    }
}