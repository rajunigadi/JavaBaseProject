package com.raju.javabaseproject.data.utils;

import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.raju.javabaseproject.R;
import com.raju.javabaseproject.ui.custom.GlideRequests;

import java.io.File;

public class ImageRequester {

    private GlideRequests glideRequests;

    public ImageRequester(GlideRequests glideRequests) {
        this.glideRequests = glideRequests;
    }

    public void loadImage(String path, ImageView imageView) {
        if(!TextUtils.isEmpty(path)){
            glideRequests.load(path)
                    .into(imageView);
        }
    }

    public void loadImage(String path, ImageView imageView, int placeholder, int size) {
        if(!TextUtils.isEmpty(path)){
            glideRequests.load(path)
                    .override(size, size)
                    .placeholder(placeholder)
                    .error(placeholder)
                    .into(imageView);
        }
    }

    public void loadImage(String path, ImageView imageView, int placeholder) {
        if(!TextUtils.isEmpty(path)) {
            glideRequests.load(path)
                    .placeholder(placeholder)
                    .error(placeholder)
                    .into(imageView);
        }
    }

    public void loadProfileImage(String path, ImageView imageView) {
        if(!TextUtils.isEmpty(path)) {
            glideRequests.load(path)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)
                    .transform(new CircleCrop())
                    .into(imageView);
        }
    }

    public void loadProfileImage(String path, ImageView imageView, int size) {
        if(!TextUtils.isEmpty(path)) {
            glideRequests.load(path)
                    .override(size, size)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)
                    .transform(new CircleCrop())
                    .into(imageView);
        }
    }

    public void loadProfileImage(Uri uri, ImageView imageView, int size) {
        if(uri != null) {
            glideRequests.load(uri)
                    .override(size, size)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)
                    .transform(new CircleCrop())
                    .into(imageView);
        }
    }

    public void loadProfileImage(File file, ImageView imageView, int size) {
        if(file != null) {
            glideRequests.load(file)
                    .override(size, size)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round)
                    .transform(new CircleCrop())
                    .into(imageView);
        }
    }

    /*public void loadGif(ImageView imageView) {
        glideRequests
                .asGif()
                .load(R.drawable.gif_image)
                .into(imageView);
    }*/
}
