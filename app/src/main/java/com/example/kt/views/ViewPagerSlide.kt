package com.example.kt.views

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created By LRD
 * on 2018/8/14  notes：自定义viewpager 不能滑动
 */
class ViewPagerSlide(context: Context,attrs:AttributeSet?) : ViewPager(context,attrs){
    private var isSlide : Boolean = false
    fun setSlide(slide:Boolean){
        isSlide = slide
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return isSlide
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return isSlide
    }
}