package com.example.sampleproject.core.ext

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.RawRes
import androidx.media3.ui.PlayerControlView
import androidx.media3.ui.PlayerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.sampleproject.R
import com.example.sampleproject.SampleApplication
import com.example.sampleproject.SampleApplication.Companion.appContext
import java.io.ByteArrayOutputStream
import java.net.URL


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

fun @receiver:RawRes Int.toUri(): Uri {
    return Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(SampleApplication.appContext().packageName)
        .appendPath("$this")
        .build()
}