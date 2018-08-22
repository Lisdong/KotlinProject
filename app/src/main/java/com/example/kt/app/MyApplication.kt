package com.example.kt.app

import android.app.Application
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheEntity
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.cookie.CookieJarImpl
import com.lzy.okgo.cookie.store.SPCookieStore
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.umeng.commonsdk.UMConfigure
import com.umeng.socialize.PlatformConfig
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.UMShareConfig
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level

/**
 * Created By LRD
 * on 2018/8/15  notes：全局初始化
 */
class MyApplication : Application(){

    companion object {
        var instance: MyApplication? = null

        const val provider = "com.example.kt.provider"
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        //全局log
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)   //（可选）是否显示线程信息。 默认值为true
                .methodCount(1)         // （可选）要显示的方法行数。 默认2
                .methodOffset(5)        // （可选）隐藏内部方法调用到偏移量。 默认5
                .tag("Kotlin-Log").build()
        Logger.addLogAdapter(object :AndroidLogAdapter(formatStrategy){
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return Constant.isDebug
            }
        })

        //网络请求
        val builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor("OkHttp-Log")       //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)     //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO)
        builder.addInterceptor(loggingInterceptor)
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)   //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)  //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)//全局的连接超时时间
        builder.cookieJar(CookieJarImpl(SPCookieStore(this)))            //使用sp保持cookie，如果cookie不过期，则一直有效
        OkGo.getInstance().init(this)                                       //必须调用初始化
                .setOkHttpClient(builder.build())                               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)                               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE).retryCount = 3    //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0

        //分享
        //UMShareAPI.get(this)
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "")
        PlatformConfig.setWeixin("wxdda8441a2fcd9957", "9dcf5851d8d807bbcf92f7dd56856eae");
        PlatformConfig.setSinaWeibo("3072140610", "faa0b4cf935fa6f3ee935b531190106c", "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("1107784636", "CGbOO5884NQ05glK");
    }
}