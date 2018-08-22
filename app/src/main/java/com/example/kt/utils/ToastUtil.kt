package com.example.kt.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

/**
 * Created by LRD on 2017/4/24.
 */

class ToastUtil private constructor(context: Context) {
    private var mToast: Toast? = null

    init {
        mToast = Toast.makeText(context.applicationContext, null, Toast.LENGTH_SHORT)
    }

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    fun showToast(toastMsg: Int) {
        mToast!!.setText(toastMsg)
        mToast!!.show()
    }

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    fun showToast(toastMsg: String) {
        mToast!!.setText(toastMsg)
        mToast!!.show()
    }

    /**
     * 取消toast，在activity的destory方法中调用
     */
    fun destroy() {
        if (null != mToast) {
            mToast!!.cancel()
            mToast = null
        }
        mToastUtil = null
    }

    companion object {
        private var mToastUtil: ToastUtil? = null

        @Synchronized
        fun getInstance(context: Context): ToastUtil {
            if (null == mToastUtil) {
                mToastUtil = ToastUtil(context)
            }
            return mToastUtil as ToastUtil
        }
    }
}
