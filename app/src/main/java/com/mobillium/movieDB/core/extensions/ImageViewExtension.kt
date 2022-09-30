package com.mobillium.movieDB.core.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions

enum class ImageScaleType {
    CENTER_CROP, CENTER_INSIDE, CIRCLE_CROP, FIT_CENTER,
}

fun ImageView.loadUrl(
    imageUrl: String?,
    imageScaleType: ImageScaleType = ImageScaleType.FIT_CENTER,
    @DrawableRes placeHolder: Int? = null,
    @DrawableRes error: Int? = null,
) {
    var options: RequestOptions = RequestOptions()
        .format(DecodeFormat.PREFER_RGB_565)
        .dontAnimate()

    options = when (imageScaleType) {
        ImageScaleType.FIT_CENTER -> options.fitCenter()
        ImageScaleType.CENTER_CROP -> options.centerCrop()
        ImageScaleType.CENTER_INSIDE -> options.centerInside()
        ImageScaleType.CIRCLE_CROP -> options.circleCrop()
    }

    if (placeHolder != null) {
        options = options.placeholder(placeHolder)
    }
    if (error != null) {
        options = options.error(error)
    }

    Glide.with(this)
        .asBitmap()
        .load(imageUrl)
        .apply(options)
        .thumbnail(0.05f)
        .into(this)
}

