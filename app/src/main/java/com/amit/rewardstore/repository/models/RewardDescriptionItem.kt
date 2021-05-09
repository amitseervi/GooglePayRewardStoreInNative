package com.amit.rewardstore.repository.models

data class RewardDescriptionItem(
    val description: String
) : BaseItem {
    override fun getType(): ModelType {
        return ModelType.REWARD_DESCRIPTION_ITEM
    }
}