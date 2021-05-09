package com.amit.rewardstore.ui.viewholder

import com.amit.rewardstore.databinding.ViewholderRewardDescriptionItemBinding
import com.amit.rewardstore.repository.models.RewardDescriptionItem

class RewardDescriptionItemViewHolder(private val binding: ViewholderRewardDescriptionItemBinding) :
    BaseViewHolder<RewardDescriptionItem>(binding.root) {
    override fun bindData(data: RewardDescriptionItem) {
        binding.tvDetailItem.text = data.description
    }
}