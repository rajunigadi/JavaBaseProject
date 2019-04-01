package com.raju.javabaseproject.utlities.image

import android.net.Uri
import android.text.TextUtils
import android.widget.ImageView

import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.raju.javabaseproject.R

import java.io.File


class ImageRequester(
        // TODO: 02/01/19 Gif Loading
        /*public void loadGif(ImageView imageView) {
        glideRequests
                .asGif()
                .load(R.drawable.gif)
                .into(imageView);
    }*/

        private val glideRequests: GlideRequests) {

    fun loadImage(path: String, imageView: ImageView) {
        if (!TextUtils.isEmpty(path)) {
            glideRequests.load(path)
                    .into(imageView)
        }
    }

    fun loadImage(path: String, imageView: ImageView, placeholder: Int, size: Int) {
        if (!TextUtils.isEmpty(path)) {
            glideRequests.load(path)
                    .override(size, size)
                    .placeholder(placeholder)
                    .error(placeholder)
                    .into(imageView)
        }
    }

    fun loadImage(path: String, imageView: ImageView, placeholder: Int) {
        if (!TextUtils.isEmpty(path)) {
            glideRequests.load(path)
                    .placeholder(placeholder)
                    .error(placeholder)
                    .into(imageView)
        }
    }

    fun loadProfileImage(path: String, imageView: ImageView) {
        if (!TextUtils.isEmpty(path)) {
            glideRequests.load(path)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .transform(CircleCrop())
                    .into(imageView)
        }
    }

    fun loadProfileImage(path: String, imageView: ImageView, size: Int) {
        if (!TextUtils.isEmpty(path)) {
            glideRequests.load(path)
                    .override(size, size)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .transform(CircleCrop())
                    .into(imageView)
        }
    }

    fun loadProfileImage(uri: Uri?, imageView: ImageView, size: Int) {
        if (uri != null) {
            glideRequests.load(uri)
                    .override(size, size)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .transform(CircleCrop())
                    .into(imageView)
        }
    }

    fun loadProfileImage(file: File?, imageView: ImageView, size: Int) {
        if (file != null) {
            glideRequests.load(file)
                    .override(size, size)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .transform(CircleCrop())
                    .into(imageView)
        }
    }
}
