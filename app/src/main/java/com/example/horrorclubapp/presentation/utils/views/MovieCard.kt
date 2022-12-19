package com.example.horrorclubapp.presentation.utils.views

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.horrorclubapp.R

@Composable
fun MovieCard(
    url: String
): Bitmap? {

    var bitmapState by remember { mutableStateOf<Bitmap?>(null) }

        Glide.with(LocalContext.current)
            .asBitmap()
            .load("https://image.tmdb.org/t/p$url")
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    bitmapState = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })

    return bitmapState
}