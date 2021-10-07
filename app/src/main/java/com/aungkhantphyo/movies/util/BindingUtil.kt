package com.aungkhantphyo.movies.util


import androidx.databinding.BindingAdapter
import com.aungkhantphyo.movies.R
import com.aungkhantphyo.movies.di.BASE_IMG_URL
import com.bumptech.glide.Glide
import com.clzola.glottie.GlottieView
import com.clzola.glottie.GlottieViewTarget

@BindingAdapter("imageUrl")
fun bindImage(imageView: GlottieView, imgUrl: String?) {

    var actualImageUrl= ""
    imgUrl?.let {
        actualImageUrl= BASE_IMG_URL+it
    }

    actualImageUrl.let {
        Glide.with(imageView.context).load(it)
            .error(R.drawable.ic_broken_image)
            .into(GlottieViewTarget(imageView))
    }
}