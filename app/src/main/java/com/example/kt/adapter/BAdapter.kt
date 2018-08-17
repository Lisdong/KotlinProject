package com.example.kt.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kt.R
import com.example.kt.bean.BrowseBean

/**
 * Created By LRD
 * on 2018/8/15  notes：首页adapter
 */
class BAdapter(layout: Int,data:List<BrowseBean.DataBean.DatasBean>) : BaseQuickAdapter<BrowseBean.DataBean.DatasBean, BaseViewHolder>(layout,data) {
    override fun convert(helper: BaseViewHolder?, item: BrowseBean.DataBean.DatasBean?) {
        helper?.setText(R.id.item_home_tv,item?.author)
        helper?.setText(R.id.item_home_context,item?.title)
        helper?.setText(R.id.item_home_time,item?.niceDate)
    }
}