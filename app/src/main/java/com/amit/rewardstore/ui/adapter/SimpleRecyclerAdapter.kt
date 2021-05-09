package com.amit.rewardstore.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amit.rewardstore.repository.models.BaseItem
import com.amit.rewardstore.ui.viewholder.BaseViewHolder

class SimpleRecyclerAdapter<T : BaseItem>(
    private val adapterBinder: BaseAdapterBinder<T>
) : RecyclerView.Adapter<BaseViewHolder<T>>() {
    private val data: MutableList<T> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return adapterBinder.createViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = data[position]
        return adapterBinder.getViewType(item)
    }

    fun updateData(newData: List<T>) {
        val callback = AdapterDiffCallback(adapterBinder, this.data, newData)
        val diffResult = DiffUtil.calculateDiff(callback)
        this.data.clear()
        this.data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class AdapterDiffCallback<T>(
        private val itemCallback: DiffUtil.ItemCallback<T>,
        private val oldList: List<T>,
        private val newList: List<T>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return itemCallback.areItemsTheSame(
                oldList[oldItemPosition]!!,
                newList[newItemPosition]!!
            )
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return itemCallback.areContentsTheSame(
                oldList[oldItemPosition]!!,
                newList[newItemPosition]!!
            )
        }
    }
}