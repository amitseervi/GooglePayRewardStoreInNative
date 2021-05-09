package com.amit.rewardstore.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.amit.rewardstore.R
import com.amit.rewardstore.databinding.FragmentHomeBinding
import com.amit.rewardstore.databinding.ViewholderRewardBinding
import com.amit.rewardstore.repository.models.RewardItem
import com.amit.rewardstore.ui.adapter.BaseViewHolderCallback
import com.amit.rewardstore.ui.adapter.SimpleRecyclerAdapter
import com.amit.rewardstore.ui.adapter.ViewHolderViewName
import com.amit.rewardstore.ui.adapter.binder.RewardItemBinder
import com.amit.rewardstore.ui.adapter.viewids.AppViewHolderViewName
import com.amit.rewardstore.ui.decorator.SimpleItemDecorator
import com.amit.rewardstore.ui.viewmodel.RewardsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), BaseViewHolderCallback<RewardItem, ViewholderRewardBinding> {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var rewardsViewModel: RewardsViewModel
    private lateinit var adapter: SimpleRecyclerAdapter<RewardItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        rewardsViewModel = ViewModelProvider(this).get(RewardsViewModel::class.java)
        adapter = SimpleRecyclerAdapter(RewardItemBinder(this))
        binding.rewardList.adapter = adapter
        val itemGap = resources.getDimensionPixelSize(R.dimen.dimen_8)
        binding.rewardList.addItemDecoration(
            SimpleItemDecorator(itemGap, itemGap, itemGap, itemGap)
        )
        registerLiveDataObserver()
        return binding.root
    }

    private fun registerLiveDataObserver() {
        rewardsViewModel.rewards.observe(viewLifecycleOwner, Observer {
            adapter.updateData(it)
        })
        rewardsViewModel.loadRewards()
    }

    override fun onItemClick(
        viewHolderViewName: ViewHolderViewName,
        data: RewardItem,
        binding: ViewholderRewardBinding
    ) {
        when (viewHolderViewName) {
            AppViewHolderViewName.ITEM_VIEW -> {
                goToRewardDetail(data, binding)
            }
        }
    }

    private fun goToRewardDetail(rewardItem: RewardItem, binding: ViewholderRewardBinding) {
        val navDirection =
            HomeFragmentDirections.actionHomeFragmentToRewardDetailDialog(rewardItem.id)
        val navigatorExtra: Navigator.Extras = FragmentNavigatorExtras(
            binding.root to "reward-${rewardItem.id}"
        )
        findNavController().navigate(navDirection, navigatorExtra)
    }
}