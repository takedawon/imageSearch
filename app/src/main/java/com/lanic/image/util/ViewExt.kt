package com.lanic.image.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.lanic.image.R

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