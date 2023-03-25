package com.example.sampleproject.core.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sampleproject.R

fun ImageView.loadImage(uri: String?) {
    Glide.with(this)
        .load(uri)
        .apply(
            RequestOptions
                .placeholderOf(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )
        .into(this)
}