package com.lanic.brandi.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lanic.brandi.R
import com.lanic.brandi.data.response.Document
import com.lanic.brandi.databinding.ItemSearchImageBinding

class SearchImageAdapter(private val clickListener: View.OnClickListener) :
    ListAdapter<Document, ImageViewHolder>(object : DiffUtil.ItemCallback<Document>() {
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
        holder.bind(getItem(position), clickListener)
    }
}

class ImageViewHolder(private val binding: ItemSearchImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(document: Document, clickListener: View.OnClickListener) {
        binding.apply {
            item = document
            ivImage.setOnClickListener(clickListener)
        }
    }
}