package com.amit.rewardstore.ui.fragments

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.amit.rewardstore.R
import com.amit.rewardstore.databinding.FragmentRewardDetailBinding
import com.amit.rewardstore.repository.models.RewardDescription
import com.amit.rewardstore.repository.models.RewardDescriptionItem
import com.amit.rewardstore.repository.models.RewardItem
import com.amit.rewardstore.ui.adapter.SimpleRecyclerAdapter
import com.amit.rewardstore.ui.adapter.binder.RewardDescriptionItemBinder
import com.amit.rewardstore.ui.decorator.SimpleItemDecorator
import com.amit.rewardstore.ui.viewmodel.RewardDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RewardDetailFragment : DialogFragment() {
    private lateinit var binding: FragmentRewardDetailBinding
    private lateinit var rewardDetailViewModel: RewardDetailViewModel
    private lateinit var adapter: SimpleRecyclerAdapter<RewardDescriptionItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let { window ->
            window.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            window.setBackgroundDrawable(
                ColorDrawable(
                    ContextCompat.getColor(window.context, R.color.transparent_black)
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRewardDetailBinding.inflate(inflater, container, false)
        rewardDetailViewModel = ViewModelProvider(this).get(RewardDetailViewModel::class.java)
        val rewardId = arguments?.getString(getString(R.string.key_args_reward_id)) ?: ""
        rewardDetailViewModel.rewardId = rewardId
        setTransitionName(rewardId)
        adapter = SimpleRecyclerAdapter(RewardDescriptionItemBinder())
        binding.contentDetail.rvDetailItems.adapter = adapter
        binding.contentDetail.rvDetailItems.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val itemGap = resources.getDimensionPixelSize(R.dimen.dimen_8)
        binding.contentDetail.rvDetailItems.addItemDecoration(
            SimpleItemDecorator(itemGap, itemGap, itemGap, itemGap)
        )
        registerLiveDataObserver()
        return binding.root
    }

    private fun setTransitionName(rewardId: String) {
        binding.contentRewardHeader.root.transitionName = "reward-${rewardId}"
    }

    private fun registerLiveDataObserver() {
        rewardDetailViewModel.reward.observe(viewLifecycleOwner, Observer {
            bindRewardHeader(it)
        })
        rewardDetailViewModel.rewardDescription.observe(viewLifecycleOwner, Observer {
            bindRewardDescription(it)
        })
        rewardDetailViewModel.loadRewardDetail()
    }

    private fun bindRewardDescription(rewardDescription: RewardDescription) {
        binding.contentDetail.tvHeader.text = rewardDescription.header
        adapter.updateData(rewardDescription.details)
    }

    private fun bindRewardHeader(reward: RewardItem) {
        binding.contentRewardHeader.tvTitle.text = reward.title
        binding.contentRewardHeader.tvSubtitle.text = reward.subtitle
    }
}