package com.example.kt.http

import android.graphics.Bitmap

/**
 * Created By LRD
 * on 2018/8/15  notes：图片下载接口回调
 */
interface ImageCallBack {
    fun onSuccess(bitmap: Bitmap)
    fun onError(string: String)
}