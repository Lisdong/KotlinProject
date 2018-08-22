package com.example.kt.ui.fragment.presenter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.example.kt.R
import com.example.kt.adapter.BAdapter
import com.example.kt.adapter.BrowseBannerAdapter
import com.example.kt.app.MessageEvent
import com.example.kt.bean.BannerBean
import com.example.kt.bean.BrowseBean
import com.example.kt.http.HttpManager
import com.example.kt.http.RequestBeanCallback
import com.example.kt.http.Url
import com.example.kt.ui.WebViewActivity
import com.example.kt.ui.fragment.model.IBrowseData
import kotlinx.android.synthetic.main.banner_layout.view.*
import kotlinx.android.synthetic.main.nek_layout.view.*
import org.greenrobot.eventbus.EventBus

/**
 * Created By LRD
 * on 2018/8/15  notes：presenter
 */
class BrowsePresenter(context: Context) :IBrowseData{

    private val mContext = context
    private var mVAdapter:BrowseBannerAdapter?=null
    private var mBannerData:List<BannerBean.DataBean> = ArrayList()
    private var mViewPager:ViewPager?=null
    private var mBox:LinearLayout?=null
    private var mScrollY = 0
    private var currScrollY = 0
    private var slidingDistance = mContext.resources.getDimensionPixelOffset(R.dimen.browse_header_height)


    //添加头部
    override fun setBannerHeader(bannerData: List<BannerBean.DataBean>) : View?{
        val view = LayoutInflater.from(mContext).inflate(R.layout.banner_layout,null)
        mViewPager = view.viewPager_banner
        mBox = view.banner_box
        setIndicator(view.banner_point,bannerData)

        mVAdapter = BrowseBannerAdapter(mContext,mBannerData)
        view.viewPager_banner.adapter = mVAdapter

        //定时轮播
        view.viewPager_banner.currentItem = (view.viewPager_banner.adapter!!.count)/2
        view.viewPager_banner.postDelayed({
            EventBus.getDefault().post(MessageEvent("Banner"));
        },4000)

        return view
    }
    override fun getNek() :View{
        val nekView = LayoutInflater.from(mContext).inflate(R.layout.nek_layout,null)
        nekView.nek_l.setOnClickListener {
            val intent = Intent()
            intent.setClass(mContext, WebViewActivity::class.java)
            intent.putExtra("url", "https://github.com/LRDDYR")
            mContext.startActivity(intent)
        }
        nekView.nek_y.setOnClickListener {
            val intent = Intent()
            intent.setClass(mContext, WebViewActivity::class.java)
            intent.putExtra("url", "https://github.com/Yexingshuai")
            mContext.startActivity(intent)
        }
        return nekView
    }


    override fun getBannerAdapter(): BrowseBannerAdapter? {
        return mVAdapter
    }

    //设置指示器
    override fun setIndicator(point:ImageView,bannerData: List<BannerBean.DataBean>) {
        mViewPager?.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageSelected(position: Int) {
                val position1 = position % mBannerData.size
                val redPointX = position1 * mContext.resources.getDimensionPixelOffset(R.dimen.home_header_point)
                val params = point.layoutParams as RelativeLayout.LayoutParams
                params.leftMargin = redPointX
                point.layoutParams = params
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

        })
    }

    //获取轮播图数据
    override fun getBannerData(listener: IDataListener) {
        HttpManager.instance?.requestGet(Url.BROWSE_BANNER_URL, BannerBean().javaClass,null,object : RequestBeanCallback<BannerBean> {
            override fun onSuccess(bean: BannerBean) {
                mBannerData = bean.data
                listener.onSuccess(bean)
            }

            override fun onError(error: String) {
                listener.onError(error)
            }

        })
    }

    //获取列表数据
    override fun getListData(currentIndex:Int,listener: IListListener) {
        HttpManager.instance?.requestGet(Url.BROWSE_LIST_URL+currentIndex+"/json", BrowseBean().javaClass,null,object : RequestBeanCallback<BrowseBean> {
            override fun onSuccess(bean: BrowseBean) {
                listener.onSuccess(bean)
            }

            override fun onError(error: String) {
                listener.onError(error)
            }
        })
    }

    //设置指示器容器数据
    override fun setBoxData(data: List<BannerBean.DataBean>) {
        mBox?.removeAllViews()
        for (i in 0 until mBannerData.size){
            val wPoint = ImageView(mContext)
            wPoint.setBackgroundResource(R.drawable.point_t_black)
            val params = LinearLayout.LayoutParams(mContext.resources.getDimensionPixelOffset(R.dimen.point_size), mContext.resources.getDimensionPixelOffset(R.dimen.point_size))
            if (i != 0){
                params.leftMargin = mContext.resources.getDimensionPixelOffset(R.dimen.point_size)
            }
            wPoint.layoutParams = params
            mBox?.addView(wPoint)
        }
    }

    //自动轮播
    override fun setAutoPlay() {
        val currentItem = mViewPager?.currentItem
        if (currentItem != null) {
            mViewPager?.setCurrentItem(currentItem+1,true)
            mViewPager?.postDelayed({
                EventBus.getDefault().post(MessageEvent("Banner"));
            },4000)
        }
    }

    //控制头部显隐
    override fun setTitleLayout(browse_title: LinearLayout, recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                mScrollY += dy
                scrollChangeHeader(browse_title)
            }
        })
    }

    private fun scrollChangeHeader(mHeader: LinearLayout) {
        val rl = mHeader.getChildAt(0) as RelativeLayout
        if (mScrollY < 0) {
            mScrollY = 0;
        }
        if (mScrollY < slidingDistance) {
            mHeader.setBackgroundColor(Color.argb(mScrollY * 255 / slidingDistance, 0x00, 0x00, 0x00))
            currScrollY = mScrollY
            rl.getChildAt(0).visibility = View.GONE
            rl.getChildAt(1).visibility = View.GONE
            rl.getChildAt(2).visibility = View.GONE
        } else {
            mHeader.setBackgroundColor(Color.argb(255, 0, 0, 0))
            rl.getChildAt(0).visibility = View.VISIBLE
            rl.getChildAt(1).visibility = View.VISIBLE
            rl.getChildAt(2).visibility = View.VISIBLE
            currScrollY = slidingDistance
        }
    }

    interface IDataListener {
        fun onSuccess(bean: BannerBean)
        fun onError(error: String)
    }

    interface IListListener {
        fun onSuccess(bean: BrowseBean)
        fun onError(error: String)
    }
}