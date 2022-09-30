package com.mobillium.movieDB.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.movieDB.core.extensions.loadUrl
import com.mobillium.movieDB.databinding.BannerItemBinding
import com.mobillium.movieDB.databinding.BannerSubItemBinding
import com.mobillium.movieDB.ui.home.model.ResultsItem
import com.mobillium.movieDB.utils.IMAGE_PATH

class BannerAdapter(private val onRowClick: (position: Int, result: ResultsItem) -> Unit) :
    ListAdapter<ResultsItem, BannerAdapter.BannerViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem) = oldItem == newItem
            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem) = oldItem == newItem
        }
    }

    private val bannerSubAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SubBannerAdapter { position, result ->
            onRowClick.invoke(position, result)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(bannerList: List<ResultsItem>?) {
        submitList(bannerList)
        bannerSubAdapter.setItems(currentList)
        bannerSubAdapter.notifyDataSetChanged()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) = holder.bind()

    override fun getItemCount(): Int = if (currentList.isNullOrEmpty()) 0 else 1

    inner class BannerViewHolder(private val binding: BannerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.viewPagerBanner.adapter = bannerSubAdapter
            binding.pagerIndicator.isVisible = currentList.size > 1
            if (binding.pagerIndicator.isVisible) {
                binding.pagerIndicator.attachToViewPager2(binding.viewPagerBanner)
            }
        }
    }
}

internal class SubBannerAdapter(private val onRowClick: (position: Int, result: ResultsItem) -> Unit) :
    ListAdapter<ResultsItem, SubBannerAdapter.SubBannerViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem) = oldItem == newItem
            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem) = oldItem == newItem
        }
    }

    fun setItems(marketBannerList: List<ResultsItem>?) = submitList(marketBannerList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubBannerViewHolder {
        val binding = BannerSubItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubBannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubBannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SubBannerViewHolder(private val binding: BannerSubItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    onRowClick.invoke(bindingAdapterPosition, getItem(bindingAdapterPosition))
                }
            }
        }

        fun bind(item: ResultsItem) {
            binding.imageView.loadUrl(IMAGE_PATH + item.backdrop_path)
            binding.tvTitle.text = item.original_title
            binding.tvDescription.text = item.overview
        }
    }
}
