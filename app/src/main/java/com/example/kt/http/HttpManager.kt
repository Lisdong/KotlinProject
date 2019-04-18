package com.example.kt.http

import android.graphics.Bitmap
import com.example.kt.app.MyApplication
import com.example.kt.utils.MyUtils
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.AbsCallback
import com.lzy.okgo.callback.BitmapCallback
import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.model.Progress
import com.lzy.okgo.model.Response
import okhttp3.OkHttpClient
import java.io.File
import java.lang.reflect.Type

/**
 * Created By LRD
 * on 2018/8/15  notes：网络工具类
 */
class HttpManager private constructor() {
    val ERROR_NET:String = "请打开网络连接"
    companion object {
        @Volatile
        private var httpManager : HttpManager? = null

        val instance : HttpManager? get() {
                if (httpManager == null){
                    synchronized(HttpManager::class.java) {
                        if (httpManager == null) {
                            httpManager = HttpManager()
                        }
                    }
                }
                return httpManager
            }
    }

    //基本请求post
    fun <T> requestPost(url: String,bean: Class<*>?,params: HashMap<String,String>?,callback: RequestBeanCallback<T>){
        if (!MyUtils.isOpenNetwork(MyApplication.instance!!)){
            callback.onError(ERROR_NET)
            return
        }
        var parameter = params
        if (parameter == null){
            parameter = HashMap()
        }
        OkGo.post<T>(url).params(parameter).execute(object : AbsCallback<T>() {
            override fun onSuccess(response: Response<T>) {
                val body = response.body()
                callback.onSuccess(body)
            }

            @Throws(Throwable::class)
            override fun convertResponse(response: okhttp3.Response): T? {
                val body = response.body() ?: return null
                var data: T? = null
                val reader = body.charStream()
                val gson = Gson()
                val jsonReader = JsonReader(reader)
                if (bean != null) data = gson.fromJson<T>(jsonReader, bean)
                return data
            }

            override fun onError(response: Response<T>) {
                val message = response.message()
                callback.onError(message)
            }
        })
    }

    //基本请求post
    fun <T> requestPost(url: String, type: Type?, params: Map<String, String>?, callback: RequestBeanCallback<T>) {
        if (!MyUtils.isOpenNetwork(MyApplication.instance!!)){
            callback.onError(ERROR_NET)
            return
        }
        var parameter = params
        if (parameter == null) {
            parameter = HashMap()
        }
        OkGo.post<T>(url).params(parameter).execute(object : AbsCallback<T>() {
            override fun onSuccess(response: Response<T>) {
                val body = response.body()
                callback.onSuccess(body)
            }

            @Throws(Throwable::class)
            override fun convertResponse(response: okhttp3.Response): T? {
                val body = response.body() ?: return null
                var data: T? = null
                val reader = body.charStream()
                val gson = Gson()
                val jsonReader = JsonReader(reader)
                if (type != null) data = gson.fromJson<T>(jsonReader, type)
                return data
            }

            override fun onError(response: Response<T>) {
                val message = response.message()
                callback.onError(message)
            }
        })
    }

    //基本请求get
    fun <T> requestGet(url: String, clazz: Class<*>?, params: Map<String, String>?, callback: RequestBeanCallback<T>) {
        if (!MyUtils.isOpenNetwork(MyApplication.instance!!)){
            callback.onError(ERROR_NET)
            return
        }
        var parameter = params
        if (parameter == null) {
            parameter = HashMap()
        }
        OkGo.get<T>(url).params(parameter).execute(object : AbsCallback<T>() {
            override fun onSuccess(response: Response<T>) {
                val body = response.body()
                callback.onSuccess(body)
            }

            @Throws(Throwable::class)
            override fun convertResponse(response: okhttp3.Response): T? {
                val body = response.body() ?: return null
                var data: T? = null
                val reader = body.charStream()
                val gson = Gson()
                val jsonReader = JsonReader(reader)
                if (clazz != null) data = gson.fromJson<T>(jsonReader, clazz)
                return data
            }

            override fun onError(response: Response<T>) {
                val message = response.message()
                callback.onError(message+"--")
            }
        })
    }

    //基本请求get
    fun <T> requestGet(url: String, type: Type?, params: Map<String, String>?, callback: RequestBeanCallback<T>) {
        if (!MyUtils.isOpenNetwork(MyApplication.instance!!)){
            callback.onError(ERROR_NET)
            return
        }
        var parameter = params
        if (parameter == null) {
            parameter = HashMap()
        }
        OkGo.get<T>(url).params(parameter).execute(object : AbsCallback<T>() {
            override fun onSuccess(response: Response<T>) {
                val body = response.body()
                callback.onSuccess(body)
            }

            @Throws(Throwable::class)
            override fun convertResponse(response: okhttp3.Response): T? {
                val body = response.body() ?: return null
                var data: T? = null
                val reader = body.charStream()
                val gson = Gson()
                val jsonReader = JsonReader(reader)
                if (type != null) data = gson.fromJson<T>(jsonReader, type)
                return data
            }

            override fun onError(response: Response<T>) {
                val message = response.message()
                callback.onError(message)
            }
        })
    }

    //基本请求，不使用javaBean
    fun requestPostString(url: String, params: Map<String, String>?, callback: RequestCallback) {
        if (!MyUtils.isOpenNetwork(MyApplication.instance!!)){
            callback.onError(ERROR_NET)
            return
        }
        var parameter = params
        if (parameter == null) {
            parameter = HashMap()
        }
        OkGo.get<String>(url).params(parameter).execute(object : AbsCallback<String>() {
            override fun onSuccess(response: Response<String>) {
                callback.onSuccess(response.body())
            }

            @Throws(Throwable::class)
            override fun convertResponse(response: okhttp3.Response): String? {
                val body = response.body() ?: return null
                //以字符串的形式获得网络
                //result = body.string();
                //以字符流的形式获得网络
                //body.charStream();

                //以字节的形式获得网络
                // body.bytes();
                //以字节流的形式获得网络
                //body.byteStream();
                return body.string()
            }

            override fun onError(response: Response<String>) {
                callback.onError(response.body())
            }
        })
    }

    //下载文件
    fun downFile(url: String, params: Map<String, String>?, callback: FileCallBack) {
        if (!MyUtils.isOpenNetwork(MyApplication.instance!!)){
            callback.onError(ERROR_NET)
            return
        }
        var parameter = params
        if (parameter == null) {
            parameter = java.util.HashMap()
        }
        OkGo.get<File>(url).params(params).execute(object : FileCallback() {
                    override fun onSuccess(response: Response<File>) {
                        callback.onSuccess(response.body())
                    }

                    override fun downloadProgress(progress: Progress?) {
                        callback.onProgress(progress)
                    }

                    override fun onError(response: Response<File>) {
                        callback.onError(response.message())
                    }
        })
    }

    //下载图片
    fun downImage(imageUrl: String, callback: ImageCallBack) {
        if (!MyUtils.isOpenNetwork(MyApplication.instance!!)){
            callback.onError(ERROR_NET)
            return
        }
        OkGo.get<Bitmap>(imageUrl).execute(object : BitmapCallback() {
            override fun onSuccess(response: Response<Bitmap>) {
                callback.onSuccess(response.body())
            }

            override fun onError(response: Response<Bitmap>) {
                callback.onError(response.message())
            }
        })
    }

    interface RequestCallback{
        fun onSuccess(body: String)
        fun onError(error: String)
    }
}