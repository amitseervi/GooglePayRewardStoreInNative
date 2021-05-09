package com.amit.rewardstore.ui.viewholder

import com.amit.rewardstore.databinding.ViewholderRewardBinding
import com.amit.rewardstore.repository.models.RewardItem
import com.amit.rewardstore.ui.adapter.BaseViewHolderCallback
import com.amit.rewardstore.ui.adapter.viewids.AppViewHolderViewName

class RewardViewHolder(
    private val binding: ViewholderRewardBinding,
    private val viewHolderCallback: BaseViewHolderCallback<RewardItem, ViewholderRewardBinding>
) :
    BaseViewHolder<RewardItem>(binding.root) {
    override fun bindData(data: RewardItem) {
        binding.tvTitle.text = data.title
        binding.tvSubtitle.text = data.subtitle
        itemView.transitionName = "reward-${data.id}"
        itemView.setOnClickListener {
            viewHolderCallback.onItemClick(AppViewHolderViewName.ITEM_VIEW, data, binding)
        }
    }
}