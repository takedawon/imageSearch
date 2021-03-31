package com.lanic.image.util

import android.app.Service
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.hardware.input.InputManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.lanic.image.R

fun Context.getPlaceHolderDrawable(): Drawable {
    return ShimmerDrawable().apply {
        setShimmer(
            Shimmer.ColorHighlightBuilder()
                .setDuration(1000)
                .setBaseColor(
                    ContextCompat.getColor(
                        this@getPlaceHolderDrawable,
                        R.color.light_transparent
                    )
                )
                .setBaseAlpha(0.7f)
                .setHighlightAlpha(0.6f)
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()
        )
    }
}

fun Context.getCircularProgressDrawable(): Drawable {
    return CircularProgressDrawable(this).apply {
        strokeWidth = 5f
        centerRadius = 30f
        setColorSchemeColors(Color.RED)
        start()
    }
}

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun View.hideKeyboard() {
    val im = context.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
    im.hideSoftInputFromWindow(windowToken, 0)
}
