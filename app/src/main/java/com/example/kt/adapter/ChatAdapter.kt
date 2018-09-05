package com.example.kt.adapter

import android.view.View
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.kt.R
import com.example.kt.bean.ServiceEntity


/**
 * Created By LRD
 * on 2018/8/31  notes：图灵聊天适配器
 */
public class ChatAdapter(mData:List<ServiceEntity>) : BaseMultiItemQuickAdapter<ServiceEntity, BaseViewHolder>(mData) {

    init {
        addItemType(ServiceEntity.TYPE_SERVICES_MESSAGE, R.layout.item_service_layout)
        addItemType(ServiceEntity.TYPE_CLIENT_MESSAGE, R.layout.item_location_layout)
    }

    override fun convert(helper: BaseViewHolder?, item: ServiceEntity?) {
        val itemType = item?.itemType
        when (itemType) {
            ServiceEntity.TYPE_SERVICES_MESSAGE -> helper?.setText(R.id.tv_main_item_content, item.data.text)
            ServiceEntity.TYPE_CLIENT_MESSAGE -> helper?.setText(R.id.tv_main_item_content, item.inputText)
        }
    }
}