package com.amit.rewardstore.repository.models

data class RewardDescription(
    val header: String,
    val details: List<RewardDescriptionItem>
)