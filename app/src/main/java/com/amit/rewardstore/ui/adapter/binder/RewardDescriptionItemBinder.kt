package com.amit.rewardstore.ui.adapter.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.amit.rewardstore.databinding.ViewholderRewardDescriptionItemBinding
import com.amit.rewardstore.repository.models.RewardDescriptionItem
import com.amit.rewardstore.ui.adapter.BaseAdapterBinder
import com.amit.rewardstore.ui.viewholder.BaseViewHolder
import com.amit.rewardstore.ui.viewholder.RewardDescriptionItemViewHolder

class RewardDescriptionItemBinder : BaseAdapterBinder<RewardDescriptionItem>() {
    override fun getViewType(item: RewardDescriptionItem): Int {
        return 0
    }

    override fun createViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<RewardDescriptionItem> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewholderRewardDescriptionItemBinding.inflate(inflater, parent, false)
        return RewardDescriptionItemViewHolder(binding)
    }

    override fun areItemsTheSame(
        oldItem: RewardDescriptionItem,
        newItem: RewardDescriptionItem
    ): Boolean {
        return oldItem.description == newItem.description
    }

    override fun areContentsTheSame(
        oldItem: RewardDescriptionItem,
        newItem: RewardDescriptionItem
    ): Boolean {
        return oldItem.description == newItem.description
    }
}