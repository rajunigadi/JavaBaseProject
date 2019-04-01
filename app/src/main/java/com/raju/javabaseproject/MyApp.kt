package com.raju.javabaseproject

import android.app.Activity
import android.app.Service
import androidx.multidex.MultiDexApplication

import com.raju.javabaseproject.dagger.components.DaggerApplicationComponent
import com.raju.javabaseproject.utlities.logger.DebugLogTree
import com.raju.javabaseproject.utlities.logger.ReleaseLogTree

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import timber.log.Timber

class MyApp : MultiDexApplication(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var serviceDispatchingAndroidInjector: DispatchingAndroidInjector<Service>

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service>? {
        return serviceDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugLogTree())
        } else {
            Timber.plant(ReleaseLogTree())
        }

        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }
}
