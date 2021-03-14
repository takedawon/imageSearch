package com.lanic.image.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lanic.image.R
import com.lanic.image.data.response.Document
import com.lanic.image.databinding.ItemSearchImageBinding

class SearchImageAdapter :
    PagedListAdapter<Document, ImageViewHolder>(object : DiffUtil.ItemCallback<Document>() {
        override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem.imageUrl == oldItem.imageUrl
        }

        override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_search_image,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }
}

class ImageViewHolder(private val binding: ItemSearchImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(document: Document) {
        binding.apply {
            item = document
            ivImage.setOnClickListener { view ->
                Navigation.findNavController(view).navigate(
                    SearchFragmentDirections.actionToSearchDetailActivity(item = document)
                )
            }
        }
    }
}