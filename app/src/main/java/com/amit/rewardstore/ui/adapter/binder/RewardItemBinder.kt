package com.amit.rewardstore.ui.adapter.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.amit.rewardstore.databinding.ViewholderRewardBinding
import com.amit.rewardstore.repository.models.RewardItem
import com.amit.rewardstore.ui.adapter.BaseAdapterBinder
import com.amit.rewardstore.ui.adapter.BaseViewHolderCallback
import com.amit.rewardstore.ui.viewholder.BaseViewHolder
import com.amit.rewardstore.ui.viewholder.RewardViewHolder

class RewardItemBinder(val viewHolderCallback: BaseViewHolderCallback<RewardItem, ViewholderRewardBinding>) :
    BaseAdapterBinder<RewardItem>() {
    override fun getViewType(item: RewardItem): Int {
        return 0
    }

    override fun createViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RewardItem> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewholderRewardBinding.inflate(inflater, parent, false)
        return RewardViewHolder(binding, viewHolderCallback)
    }

    override fun areItemsTheSame(oldItem: RewardItem, newItem: RewardItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RewardItem, newItem: RewardItem): Boolean {
        return oldItem == newItem
    }
}