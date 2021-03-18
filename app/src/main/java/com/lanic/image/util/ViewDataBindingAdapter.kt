package com.lanic.image.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.lanic.image.R

@BindingAdapter(value = ["image_url"])
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(imageView.context.getPlaceHolderDrawable())
        .error(R.drawable.ic_error_404)
        .into(imageView)
}

@BindingAdapter(value = ["loading_image_url"])
fun setLoadingImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(imageView.context.getCircularProgressDrawable())
        .error(R.drawable.ic_error_404)
        .into(imageView)
}


@BindingAdapter(value = ["view_visibility"])
fun setViewVisibility(view: View, url: String?) {
    if (url.isNullOrEmpty()) {
        view.visibility = View.INVISIBLE
    } else {
        view.visibility = View.VISIBLE
    }
}
