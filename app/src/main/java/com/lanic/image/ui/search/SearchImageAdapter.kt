package com.lanic.image.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lanic.image.R
import com.lanic.image.data.response.SearchImage
import com.lanic.image.databinding.ItemProgressBarBinding
import com.lanic.image.databinding.ItemSearchImageBinding

class SearchImageAdapter :
    PagedListAdapter<SearchImage, RecyclerView.ViewHolder>(object :
        DiffUtil.ItemCallback<SearchImage>() {
        override fun areItemsTheSame(oldItem: SearchImage, newItem: SearchImage): Boolean {
            return oldItem.imageUrl == oldItem.imageUrl
        }

        override fun areContentsTheSame(oldItem: SearchImage, newItem: SearchImage): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_search_image,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImageViewHolder -> {
                getItem(position)?.let { item ->
                    holder.bind(item)
                }
            }
        }
    }
}

class ImageViewHolder(private val binding: ItemSearchImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(searchImage: SearchImage) {
        binding.apply {
            item = searchImage
            ivImage.setOnClickListener { view ->
                Navigation.findNavController(view).navigate(
                    SearchFragmentDirections.actionToSearchDetailActivity(item = searchImage)
                )
            }
        }
    }
}
