package com.example.kt.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException

import com.example.kt.IWebviewService
import com.example.kt.bean.User

class WebViewService : Service() {

    private val iWebviewService = object : IWebviewService.Stub() {
        @Throws(RemoteException::class)
        override fun getUserInfo(): User {
            return User(10010, "菊花星")
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return iWebviewService
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return Service.START_STICKY
    }

}
