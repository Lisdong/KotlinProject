package com.example.kt.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kt.bean.BannerBean
import com.example.kt.ui.WebViewActivity

/**
 * Created By LRD
 * on 2018/8/16  notes：轮播图adapter
 */
class BrowseBannerAdapter(private var context: Context,private var data:List<BannerBean.DataBean>?) :PagerAdapter(){

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return data!!.size * 100 * 10
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imagePath = data!![position % data!!.size].imagePath
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        Glide.with(context).load(imagePath).into(imageView)
        container.addView(imageView)
        imageView.setOnClickListener{
            val intent = Intent()
            intent.setClass(context, WebViewActivity::class.java)
            intent.putExtra("url", data!![position % data!!.size].url)
            context.startActivity(intent)
        }
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    fun setData(datas:List<BannerBean.DataBean>){
        data = datas
        notifyDataSetChanged()
    }
}