package com.amit.rewardstore.repository

import android.app.Application
import com.amit.rewardstore.repository.models.RewardDescription
import com.amit.rewardstore.repository.models.RewardItem
import com.amit.rewardstore.repository.models.RewardModel
import com.amit.rewardstore.utils.FileUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import javax.inject.Singleton

private const val FILE_NAME_REWARDS = "rewards.json"

@Singleton
class RewardsRepository @Inject constructor(
    private val context: Application,
    private val gson: Gson
) {
    private val rewardItemCache: MutableMap<String, RewardItem> = mutableMapOf()
    private val rewardDescriptionCache: MutableMap<String, RewardDescription> = mutableMapOf()

    fun loadRewards(): List<RewardItem> {
        if (rewardItemCache.isNotEmpty()) {
            return rewardItemCache.values.toList()
        }
        loadFromFile()
        return rewardItemCache.values.toList()
    }

    fun getReward(id: String): RewardItem {
        if (rewardItemCache.containsKey(id)) {
            return rewardItemCache[id]!!
        }
        loadFromFile()
        return rewardItemCache[id]!!
    }

    fun getRewardDescription(id: String): RewardDescription {
        if (rewardDescriptionCache.containsKey(id)) {
            return rewardDescriptionCache[id]!!
        }
        loadFromFile()
        return rewardDescriptionCache[id]!!
    }

    private fun loadFromFile() {
        val type = object : TypeToken<List<RewardModel>>() {}.type
        val rewards: List<RewardModel> =
            gson.fromJson(FileUtils.readFileFromAsset(FILE_NAME_REWARDS, context), type)
        rewards.forEach {
            rewardItemCache[it.id] = it.toRewardItem()
            rewardDescriptionCache[it.id] = it.description
        }
    }
}