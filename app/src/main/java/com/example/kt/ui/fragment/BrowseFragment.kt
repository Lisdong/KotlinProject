package com.example.kt.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.kt.R
import com.example.kt.R.layout.f_browse_layout
import com.example.kt.adapter.BAdapter
import com.example.kt.adapter.BrowseBannerAdapter
import com.example.kt.app.MessageEvent
import com.example.kt.bean.BannerBean
import com.example.kt.bean.BrowseBean
import com.example.kt.ui.WebViewActivity
import com.example.kt.ui.base.BaseFragment
import com.example.kt.ui.fragment.presenter.BrowsePresenter
import com.example.kt.ui.fragment.view.BrowseView
import kotlinx.android.synthetic.main.f_browse_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created By LRD
 * on 2018/8/14  notes：首页
 */
class BrowseFragment : BaseFragment(),BrowseView{

    private lateinit var mPresenter : BrowsePresenter
    private var mData : List<BrowseBean.DataBean.DatasBean> = ArrayList()
    private var mBannerData : List<BannerBean.DataBean> = ArrayList()
    private lateinit var mBAdapter : BAdapter
    private lateinit var mVAdapter : BrowseBannerAdapter
    private var currentIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //注册事件
        EventBus.getDefault().register(this)
    }

    override fun setLayout(): Int {
        return f_browse_layout
    }

    override fun init() {
        mPresenter = BrowsePresenter(activity)

        initView()
        initListener()
    }

    private fun initView() {
        val layoutManager =LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        b_recyclerView.layoutManager = layoutManager

        mBAdapter = BAdapter(R.layout.item_hoem_layout,mData)
        mBAdapter.openLoadAnimation()//默认为渐显效果
        b_recyclerView.adapter = mBAdapter

        //添加头部
        mBAdapter.addHeaderView(getBannerHeader())

        //添加颈部
        mBAdapter.addHeaderView(getNek())

        getBannerAdapter()
        showData()
        showBanner()
        setTitleShow()
    }

    override fun getBannerAdapter() {
        mVAdapter = mPresenter.getBannerAdapter()!!
    }

    fun getNek():View{
        return mPresenter.getNek()
    }

    override fun showData() {
        mPresenter.getListData(currentIndex,object :BrowsePresenter.IListListener{
            override fun onSuccess(bean: BrowseBean) {
                if (bean.data.datas != null && bean.data.datas.size > 0){
                    if (currentIndex == 1){
                        mBAdapter.setNewData(bean.data.datas)
                    }else{
                        mBAdapter.addData(bean.data.datas);
                        mBAdapter.loadMoreComplete();
                    }
                    currentIndex += 1;
                }
            }

            override fun onError(error: String) {

            }
        })
    }

    override fun showBanner() {
        mPresenter.getBannerData(object :BrowsePresenter.IDataListener{
            override fun onSuccess(bean: BannerBean) {
                mVAdapter.setData(bean.data)
                mBannerData = bean.data
                mPresenter.setBoxData(bean.data)
            }

            override fun onError(error: String) {
            }

        })
    }

    override fun setTitleShow() {
        mPresenter.setTitleLayout(browse_title,b_recyclerView)
    }

    override fun getBannerHeader():View? {
        return mPresenter.setBannerHeader(mBannerData)
    }

    private fun initListener() {
        mBAdapter.setOnLoadMoreListener({
            showData()
        },b_recyclerView)
        //默认第一次加载会调用  加载更多，此方法控制第一次不进入加载回调
        mBAdapter.disableLoadMoreIfNotFullPage();
        mBAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val data:List<BrowseBean.DataBean.DatasBean> = adapter.data as List<BrowseBean.DataBean.DatasBean>
            val intent = Intent()
            intent.setClass(activity, WebViewActivity::class.java)
            intent.putExtra("url", data[position].link)
            startActivity(intent)
        }
        b_refresh.setOnRefreshListener {
            currentIndex = 1
            showData()
            showBanner()
            b_refresh.isRefreshing = false
        }
        browse_search.setOnClickListener {
            val intent = Intent()
            intent.setClass(activity, WebViewActivity::class.java)
            intent.putExtra("url", "https://www.baidu.com")
            startActivity(intent)
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun  Event(messageEvent:MessageEvent) {
        while (messageEvent.mMessage == "Banner") {
            mPresenter.setAutoPlay()
            break
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this);
    }
}