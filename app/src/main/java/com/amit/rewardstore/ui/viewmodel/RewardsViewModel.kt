package com.amit.rewardstore.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amit.rewardstore.repository.RewardsRepository
import com.amit.rewardstore.repository.models.RewardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class RewardsViewModel @Inject constructor(
    private val rewardsRepository: RewardsRepository
) : ViewModel() {
    private val viewModelCoroutineContext = SupervisorJob() + Dispatchers.Main
    private val viewModelScope = CoroutineScope(viewModelCoroutineContext)

    private val _rewards: MutableLiveData<List<RewardItem>> = MutableLiveData()
    val rewards: LiveData<List<RewardItem>>
        get() = _rewards

    fun loadRewards() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val rewards = rewardsRepository.loadRewards()
                postRewards(rewards)
            }
        }
    }

    suspend fun postRewards(rewards: List<RewardItem>) = withContext(Dispatchers.Main) {
        _rewards.postValue(rewards)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel("View model cleared")
    }
}