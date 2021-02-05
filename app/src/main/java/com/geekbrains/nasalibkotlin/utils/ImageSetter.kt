package com.geekbrains.nasalibkotlin.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageSetter {
    fun setImage(url: String?, imageView: ImageView){
        Picasso
            .get()
            .load(url)
            .into(imageView)
    }
}