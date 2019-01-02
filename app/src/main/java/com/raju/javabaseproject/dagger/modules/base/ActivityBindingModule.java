package com.raju.javabaseproject.dagger.modules.base;

import com.raju.javabaseproject.dagger.PerActivity;
import com.raju.javabaseproject.ui.activities.MainActivity;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

@Module(includes = AndroidInjectionModule.class)
public abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

}
