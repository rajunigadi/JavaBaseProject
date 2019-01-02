package com.raju.javabaseproject.utlities.image;

import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.raju.javabaseproject.R;

import java.io.File;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

public class ImageRequester {

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
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .transform(new CircleCrop())
                    .into(imageView);
        }
    }

    public void loadProfileImage(String path, ImageView imageView, int size) {
        if(!TextUtils.isEmpty(path)) {
            glideRequests.load(path)
                    .override(size, size)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .transform(new CircleCrop())
                    .into(imageView);
        }
    }

    public void loadProfileImage(Uri uri, ImageView imageView, int size) {
        if(uri != null) {
            glideRequests.load(uri)
                    .override(size, size)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .transform(new CircleCrop())
                    .into(imageView);
        }
    }

    public void loadProfileImage(File file, ImageView imageView, int size) {
        if(file != null) {
            glideRequests.load(file)
                    .override(size, size)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .transform(new CircleCrop())
                    .into(imageView);
        }
    }

    // TODO: 02/01/19 Gif Loading
    /*public void loadGif(ImageView imageView) {
        glideRequests
                .asGif()
                .load(R.drawable.gif)
                .into(imageView);
    }*/

    private GlideRequests glideRequests;
}
