package com.mobillium.movieDB.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobillium.movieDB.core.extensions.loadUrl
import com.mobillium.movieDB.databinding.MovieListItemBinding
import com.mobillium.movieDB.ui.home.model.ResultsItem
import com.mobillium.movieDB.utils.IMAGE_PATH

internal class MovieListAdapter(private val onRowClick: (position: Int, result: ResultsItem) -> Unit) :
    ListAdapter<ResultsItem, MovieListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem) = oldItem == newItem
            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem) = oldItem == newItem
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(marketBannerList: List<ResultsItem>?) {
        submitList(marketBannerList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    onRowClick.invoke(bindingAdapterPosition, getItem(bindingAdapterPosition))
                }
            }
        }

        fun bind(item: ResultsItem) {
            binding.imageView.loadUrl(IMAGE_PATH + item.backdrop_path)
            binding.tvDate.text = item.release_date
            binding.tvTitle.text = item.original_title
            binding.tvDescription.text = item.overview
        }
    }
}
