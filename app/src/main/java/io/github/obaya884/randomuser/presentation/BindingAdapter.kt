package io.github.obaya884.randomuser.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

object BindingAdapter {
    @BindingAdapter("icon_image")
    @JvmStatic
    fun loadImage(view: ImageView, url: String) {
        view.load(url)
    }
}