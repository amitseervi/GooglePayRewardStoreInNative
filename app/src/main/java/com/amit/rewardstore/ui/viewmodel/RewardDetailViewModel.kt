package com.amit.rewardstore.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amit.rewardstore.repository.RewardsRepository
import com.amit.rewardstore.repository.models.RewardDescription
import com.amit.rewardstore.repository.models.RewardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class RewardDetailViewModel @Inject constructor(
    private val rewardsRepository: RewardsRepository
) : ViewModel() {
    private val viewModelCoroutineContext = SupervisorJob() + Dispatchers.Main
    private val viewModelScope = CoroutineScope(viewModelCoroutineContext)
    var rewardId: String? = null

    private val _reward: MutableLiveData<RewardItem> = MutableLiveData()
    val reward: LiveData<RewardItem>
        get() = _reward

    private val _rewardDetail: MutableLiveData<RewardDescription> = MutableLiveData()
    val rewardDescription: LiveData<RewardDescription>
        get() = _rewardDetail

    fun loadRewardDetail() {
        val rewardId = this.rewardId ?: return
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val reward = rewardsRepository.getReward(rewardId)
                postRewardItem(reward)
                val rewardDetail = rewardsRepository.getRewardDescription(rewardId)
                postRewardDetail(rewardDetail)
            }
        }
    }

    private suspend fun postRewardDetail(rewardDetail: RewardDescription) =
        withContext(Dispatchers.Main) {
            _rewardDetail.postValue(rewardDetail)
        }

    private suspend fun postRewardItem(reward: RewardItem) = withContext(Dispatchers.Main) {
        _reward.postValue(reward)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel("View model cleared")
    }
}