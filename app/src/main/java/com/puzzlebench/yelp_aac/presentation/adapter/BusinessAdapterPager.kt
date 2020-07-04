package com.puzzlebench.yelp_aac.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.puzzlebench.yelp_aac.data.local.room.entity.BusinessEntity
import com.puzzlebench.yelp_aac.databinding.ListBusinessItemPaggerBinding

class BusinessAdapterPager :
    PagedListAdapter<BusinessEntity, RecyclerView.ViewHolder>(BusinessAdapterCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BusinessViewHolder(
            ListBusinessItemPaggerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as BusinessViewHolder).bind(it)
        }
    }

    class BusinessViewHolder(private val binding: ListBusinessItemPaggerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.business?.let { business ->
                }
            }
        }

        fun bind(item: BusinessEntity) {
            binding.apply {
                business = item
                executePendingBindings()
            }
        }
    }
}

private class BusinessAdapterCallback : DiffUtil.ItemCallback<BusinessEntity>() {

    override fun areItemsTheSame(oldItem: BusinessEntity, newItem: BusinessEntity): Boolean {
        return oldItem.businessId == newItem.businessId
    }

    override fun areContentsTheSame(oldItem: BusinessEntity, newItem: BusinessEntity): Boolean {
        return oldItem == newItem
    }
}
