package com.raju.javabaseproject.di.modules.base;

import com.raju.javabaseproject.di.PerActivity;
import com.raju.javabaseproject.ui.activities.MainActivity;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

@Module(includes = AndroidInjectionModule.class)
public abstract class ActivityModule {
    @PerActivity
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();
}
