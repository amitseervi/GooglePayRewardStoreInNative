package com.amit.rewardstore.repository.models

data class RewardModel(
    val id: String,
    val title: String,
    val subTitle: String,
    val description: RewardDescription
)