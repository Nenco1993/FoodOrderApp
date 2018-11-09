package com.example.neven.foodorderapp.food

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide


@BindingAdapter("imageURL")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
            .load(url)
            .into(view)
}
