package com.amit.rewardstore.ui.adapter

import androidx.viewbinding.ViewBinding

interface BaseViewHolderCallback<T, V : ViewBinding> {
    fun onItemClick(viewHolderViewName: ViewHolderViewName, data: T, binding: V)
}