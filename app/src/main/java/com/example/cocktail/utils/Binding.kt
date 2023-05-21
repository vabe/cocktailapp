package com.example.cocktail.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view).load(url).into(view)
    }
}