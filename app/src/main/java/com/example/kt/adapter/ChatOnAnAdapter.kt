package com.example.kt.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kt.R
import com.example.kt.bean.ServiceEntity

/**
 * Created By LRD
 * on 2018/9/5  notes：
 */
class ChatOnAnAdapter(mData:ArrayList<ServiceEntity>) : BaseMultiItemQuickAdapter<ServiceEntity, BaseViewHolder>(mData) {
    init {
        addItemType(ServiceEntity.TYPE_SERVICES_MESSAGE, R.layout.item_service_layout)
        addItemType(ServiceEntity.TYPE_CLIENT_MESSAGE, R.layout.item_location_layout)
    }
    override fun convert(helper: BaseViewHolder?, item: ServiceEntity?) {
        val itemType = item?.itemType
        when (itemType) {
            ServiceEntity.TYPE_SERVICES_MESSAGE -> helper?.setText(R.id.tv_main_item_content, item.dataForAn.result)
            ServiceEntity.TYPE_CLIENT_MESSAGE -> helper?.setText(R.id.tv_main_item_content, item.inputText)
        }
    }
}