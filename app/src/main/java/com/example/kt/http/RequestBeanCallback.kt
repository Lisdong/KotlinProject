package com.example.kt.http

/**
 * Created By LRD
 * on 2018/8/15  notes：返回接口
 */
interface RequestBeanCallback<T> {
    fun onSuccess(bean:T)
    fun onError(error:String)
}