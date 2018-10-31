package com.raju.javabaseproject.di.modules.base;

import android.content.Context;

import com.raju.javabaseproject.data.utils.ImageRequester;
import com.raju.javabaseproject.ui.custom.GlideApp;
import com.raju.javabaseproject.ui.custom.GlideRequests;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {

    @Provides
    @Singleton
    static GlideRequests provideGlideApp(Context context) {
        return GlideApp .with(context);
    }

    @Provides
    @Singleton
    ImageRequester provideGlideImageRequester(GlideRequests glideRequests) {
        return new ImageRequester(glideRequests);
    }


    /*@Provides
    @Singleton
    static Picasso provideDefaultPicasso(Context context, OkHttpClient client) {
        return buildPicasso(context, client);
    }

    @Provides
    @Singleton
    ImageRequester provideImageRequester(Picasso picasso) {
        return new ImageRequester(picasso);
    }

    private static Picasso buildPicasso(Context context, OkHttpClient client) {
        return buildPicasso(context, client, null);
    }

    private static Picasso buildPicasso(Context context, OkHttpClient client, Picasso.Listener errorListener) {
        Picasso.Builder picassoBuilder = new Picasso.Builder(context);
        //picassoBuilder.indicatorsEnabled(BuildConfig.DEBUG_IMAGES);
        //picassoBuilder.loggingEnabled(BuildConfig.DEBUG_IMAGES);
        if (isLowMemoryDevice(context)){
            picassoBuilder.defaultBitmapConfig(Bitmap.Config.RGB_565);
        }
        picassoBuilder.downloader(new OkHttp3Downloader(client));
        if (errorListener != null) {
            picassoBuilder.listener(errorListener);
        }
        return picassoBuilder.build();
    }

    @Provides
    @Singleton
    static RequestManager provideGlideApp(Context context) {
        return Glide.with(context);
    }

    @Provides
    @Singleton
    ImageRequester provideGlideImageRequester(RequestManager requestManager) {
        return new ImageRequester(picasso);
    }

    private static boolean isLowMemoryDevice(Context context) {
        return ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).isLowRamDevice();
    }*/
}
