package com.amit.rewardstore.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.amit.rewardstore.repository.models.BaseItem
import com.amit.rewardstore.ui.viewholder.BaseViewHolder

abstract class BaseAdapterBinder<T : BaseItem> : DiffUtil.ItemCallback<T>() {
    abstract fun getViewType(item: T): Int
    abstract fun createViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>
}