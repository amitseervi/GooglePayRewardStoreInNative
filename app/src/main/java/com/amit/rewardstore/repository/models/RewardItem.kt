package com.amit.rewardstore.repository.models

data class RewardItem(
    val id: String,
    val title: String,
    val subtitle: String
) : BaseItem {
    override fun getType(): ModelType {
        return ModelType.REWARD
    }
}