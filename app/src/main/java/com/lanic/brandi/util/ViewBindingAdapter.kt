package com.lanic.brandi.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.lanic.brandi.R

@BindingAdapter(value = ["image_url"])
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

@BindingAdapter(value = ["view_visibility"])
fun setViewVisibility(textView: TextView, url: String?) {
    if (url.isNullOrEmpty()) {
        textView.visibility = View.INVISIBLE
    } else {
        textView.visibility = View.VISIBLE
    }
}