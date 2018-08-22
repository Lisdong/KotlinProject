package com.example.kt.java

import android.content.Context
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * Created By LRD
 * on 2018/8/20  notes：
 */
class Test {
    private fun hh(context: Context) {
        val webView = WebView(context)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    }
}
