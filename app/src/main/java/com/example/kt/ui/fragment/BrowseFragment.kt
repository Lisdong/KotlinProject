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
import com.example.kt.utils.ToastUtil
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import com.umeng.socialize.media.UMWeb
import kotlinx.android.synthetic.main.empty_layout.view.*
import kotlinx.android.synthetic.main.f_browse_layout.*
import kotlinx.android.synthetic.main.web_layout.*
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
//
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

    private fun getEmptyView(): View {
        mBAdapter.removeAllHeaderView()
        val view = layoutInflater.inflate(R.layout.empty_layout,null)
        view.error_img.setOnClickListener{
            showData()
            showBanner()
        }
        return view
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
                mBAdapter.emptyView = getEmptyView()
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
                //mBAdapter.emptyView = getEmptyView()
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
//            val web = UMWeb("https://www.baidu.com")
//            val image = UMImage(activity, R.mipmap.ic_launcher)
//            web.title = "来自kotlin学习app"
//            web.setThumb(image)
//            web.description = "hello"
//            ShareAction(activity)
//                    .withMedia(web)
//                    .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN)
//                    .setCallback(object :UMShareListener{
//                        override fun onCancel(p0: SHARE_MEDIA?) {
//                            ToastUtil.getInstance(activity).showToast("分享取消啦")
//
//                        }
//
//                        override fun onError(p0: SHARE_MEDIA?, p1: Throwable?) {
//                            ToastUtil.getInstance(activity).showToast("分享失败啦-$p1")
//                        }
//
//                        override fun onStart(p0: SHARE_MEDIA?) {
//                        }
//
//                        override fun onResult(p0: SHARE_MEDIA?) {
//                            ToastUtil.getInstance(activity).showToast("分享成功啦")
//                        }
//
//                    })
//                    .open()
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