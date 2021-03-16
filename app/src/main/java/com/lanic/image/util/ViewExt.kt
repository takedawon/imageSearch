package com.lanic.image.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.lanic.image.R

@BindingAdapter(value = ["image_url"])
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(imageView.context.getPlaceHolderDrawable())
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

fun Context.getPlaceHolderDrawable(): Drawable {
    return ShimmerDrawable().apply {
        setShimmer(
            Shimmer.ColorHighlightBuilder()
                .setDuration(1000)
                .setBaseColor(ContextCompat.getColor(this@getPlaceHolderDrawable, R.color.light_transparent))
                .setBaseAlpha(0.7f)
                .setHighlightAlpha(0.6f)
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()
        )
    }
}

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}