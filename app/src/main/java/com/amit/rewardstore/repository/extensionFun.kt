package com.amit.rewardstore.repository

import com.amit.rewardstore.repository.models.RewardItem
import com.amit.rewardstore.repository.models.RewardModel

fun RewardModel.toRewardItem(): RewardItem {
    return RewardItem(
        id = this.id,
        title = this.title,
        subtitle = this.subTitle
    )
}

fun RewardItem.getTransitionName(): String {
    return "reward-${this.id}"
}