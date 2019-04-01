package com.raju.javabaseproject.dagger.modules.base

import android.app.ActivityManager
import android.content.Context

import com.raju.javabaseproject.utlities.image.GlideApp
import com.raju.javabaseproject.utlities.image.GlideRequests
import com.raju.javabaseproject.utlities.image.ImageRequester

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class ImageModule {

    @Provides
    @Singleton
    internal fun provideGlideImageRequester(glideRequests: GlideRequests): ImageRequester {
        return ImageRequester(glideRequests)
    }

    @Provides
    @Singleton
    internal fun provideGlideApp(context: Context): GlideRequests {
        return GlideApp.with(context)
    }

    private fun isLowMemoryDevice(context: Context): Boolean {
        return (context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).isLowRamDevice
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
