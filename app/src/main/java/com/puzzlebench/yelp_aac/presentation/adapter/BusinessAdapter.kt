package com.puzzlebench.yelp_aac.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzlebench.yelp_aac.databinding.ListBusinessItemBinding
import com.puzzlebench.yelp_aac.presentation.fragment.ListBusinessesFragmentDirections
import com.puzzlebench.yelp_aac.presentation.model.Business

class BusinessAdapter : ListAdapter<Business, RecyclerView.ViewHolder>(BusinessDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BusinessViewHolder(
            ListBusinessItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BusinessViewHolder).bind(getItem(position))
    }

    class BusinessViewHolder(private val binding: ListBusinessItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                Toast.makeText(binding.root.context,"toast",Toast.LENGTH_LONG).show()
                /**binding.business?.let { business ->
                    navigateBussinesDetails(business, it)
                }**/
            }
        }

        fun bind(item: Business) {
            binding.apply {
                business = item
                executePendingBindings()
            }
        }

        private fun navigateBussinesDetails(business: Business, it: View) {
            val action =
                ListBusinessesFragmentDirections.actionMainFragmentToDetailsBusinessFragment(
                    business.businessId
                )
            Navigation.findNavController(it).navigate(action)
        }
    }
}

private class BusinessDiffCallback : DiffUtil.ItemCallback<Business>() {

    override fun areItemsTheSame(oldItem: Business, newItem: Business): Boolean {
        return oldItem.businessId == newItem.businessId
    }

    override fun areContentsTheSame(oldItem: Business, newItem: Business): Boolean {
        return oldItem == newItem
    }
}
