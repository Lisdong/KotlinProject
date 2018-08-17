package com.example.kt.http

import com.lzy.okgo.model.Progress
import java.io.File

/**
 * Created By LRD
 * on 2018/8/15  notes：文件下载接口回调
 */
interface FileCallBack {
    fun onSuccess(file: File)
    fun onError(string: String)
    fun onProgress(progress: Progress?)
}