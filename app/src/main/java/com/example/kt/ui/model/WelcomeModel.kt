package com.example.kt.ui.model

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.example.kt.R
import com.example.kt.ui.HomeActivity
import com.example.kt.ui.WelcomeActivity
import com.example.kt.ui.model.callback.WeMImpl
import com.example.kt.utils.DeviceUtils

/**
 * Created By LRD
 * on 2018/8/10  notes：欢迎页 业务实现类
 */
class WelcomeModel : WeMImpl {

    var mContext : Activity? = null
    var mViewPager : ViewPager? = null
    var mBox : LinearLayout? = null
    var mPoint : ImageView? = null
    var mBtn : Button? = null
    var mImages = ArrayList<ImageView>()

    //初始化数据
    override fun initData(context: Activity, wc_vp: ViewPager, wc_box: LinearLayout, wc_point: ImageView, wc_btn: Button) {
        mContext = context
        mViewPager = wc_vp
        mBox = wc_box
        mPoint = wc_point
        mBtn = wc_btn

        val imageList = intArrayOf(R.drawable.pictrue1,R.drawable.pictrue2,R.drawable.pictrue3,R.drawable.pictrue4)
        for (i in 0 until imageList.size){
            val imageView = ImageView(mContext)
            imageView.setBackgroundResource(imageList[i])
            mImages.add(imageView)

            val wPoint = ImageView(mContext)
            wPoint.setBackgroundResource(R.drawable.point_t_black)
            val ml = DeviceUtils.dip2px(mContext,8f)
            val params = LinearLayout.LayoutParams(ml,ml)
            if (i != 0){
                params.leftMargin = ml
            }
            wPoint.layoutParams = params
            mBox!!.addView(wPoint)
        }
    }

    //绑定适配器
    override fun initView() {
        mViewPager?.adapter = SPVPAdapter(mImages)
    }

    //监听
    override fun initListener() {
        mViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val ml = DeviceUtils.dip2px(mContext,16f)
                val mPointX = (positionOffset + position) * ml
                val params = RelativeLayout.LayoutParams(DeviceUtils.dip2px(mContext,8f),DeviceUtils.dip2px(mContext,8f))
                params.leftMargin = mPointX.toInt()
                mPoint?.layoutParams = params

            }

            override fun onPageSelected(position: Int) {
                if (position == mImages.size-1){
                    mBtn?.visibility = View.VISIBLE
                    val animator =  ObjectAnimator.ofFloat(mBtn, "alpha", 0f,1f)
                    animator.duration = 2000
                    animator.start()
                }else{
                    mBtn?.visibility = View.GONE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

        mBtn?.setOnClickListener{
            skipActivity()
        }
    }

    //页面跳转
    override fun skipActivity() {
        val intent= Intent()
        intent.setClass(mContext,HomeActivity::class.java)
        mContext?.startActivity(intent)
        mContext?.finish()
    }

    //适配器
    class SPVPAdapter(images: ArrayList<ImageView>) : PagerAdapter(){
        var a : ArrayList<ImageView>? = null
        init {
            a = images
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return a!!.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = a!!.get(position)
            container.addView(imageView)
            return imageView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }
    }
}