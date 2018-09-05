package com.example.kt.kotlin

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kt.R
import com.example.kt.bean.ServiceEntity

/**
 * Created By LRD
 * on 2018/8/31  notes：
 */
class Chaa(data: List<ServiceEntity>) : BaseMultiItemQuickAdapter<ServiceEntity, BaseViewHolder>(data) {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    init {
        addItemType(ServiceEntity.TYPE_SERVICES_MESSAGE, R.layout.item_hoem_layout)
        addItemType(ServiceEntity.TYPE_CLIENT_MESSAGE, R.layout.item_hoem_layout)
    }

    override fun convert(helper: BaseViewHolder, item: ServiceEntity) {

    }
}