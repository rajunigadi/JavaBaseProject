package com.raju.javabaseproject.dagger.modules.base;

import com.raju.javabaseproject.dagger.PerActivity;
import com.raju.javabaseproject.ui.activities.MainActivity;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

@Module(includes = AndroidInjectionModule.class)
public abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

}
