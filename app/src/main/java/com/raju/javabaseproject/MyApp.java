package com.raju.javabaseproject;

import android.app.Activity;
import android.app.Service;
import android.support.multidex.MultiDexApplication;

import com.raju.javabaseproject.dagger.components.DaggerApplicationComponent;
import com.raju.javabaseproject.utlities.logger.DebugLogTree;
import com.raju.javabaseproject.utlities.logger.ReleaseLogTree;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import timber.log.Timber;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

public class MyApp extends MultiDexApplication implements HasActivityInjector, HasServiceInjector {

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugLogTree());
        } else {
            Timber.plant(new ReleaseLogTree());
        }

        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Service> serviceDispatchingAndroidInjector;
}
