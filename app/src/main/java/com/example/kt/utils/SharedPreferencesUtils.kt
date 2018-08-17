package com.example.kt.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by LRD on 2017/9/14.
 */

object SharedPreferencesUtils {
    private var sp: SharedPreferences? = null

    /**
     * 保存boolean信息的操作
     */
    fun saveBoolean(context: Context, key: String, value: Boolean) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().putBoolean(key, value).apply()
    }

    /**
     * 获取boolean信息的操作
     */
    fun getBoolean(context: Context, key: String, defValue: Boolean): Boolean {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getBoolean(key, defValue)
    }

    /**
     * 保存String信息的操作
     */
    fun saveString(context: Context, key: String, value: String) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().putString(key, value).apply()
    }

    /**
     * 获取String信息的操作
     */
    fun getString(context: Context, key: String, defValue: String): String? {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getString(key, defValue)
    }

    /**
     * 保存Int信息的操作
     */
    fun saveInt(context: Context, key: String, value: Int) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().putInt(key, value).apply()
    }

    /**
     * 获取Int信息的操作
     */
    fun getInt(context: Context, key: String, defValue: Int): Int {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }

        return sp!!.getInt(key, defValue)
    }

    /**
     * 保存long信息的操作
     */
    fun saveLong(context: Context, key: String, value: Long) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().putLong(key, value).apply()
    }

    /**
     * 获取long信息的操作
     */
    fun getLong(context: Context, key: String, defValue: Long): Long {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }

        return sp!!.getLong(key, defValue)
    }

    /**
     * 移除一个数据
     */
    fun remove(context: Context, key: String) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().remove(key).apply()
    }

}
