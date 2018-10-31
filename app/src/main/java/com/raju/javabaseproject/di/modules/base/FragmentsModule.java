package com.raju.javabaseproject.di.modules.base;

import com.raju.javabaseproject.di.PerFragment;
import com.raju.javabaseproject.di.modules.UserModule;
import com.raju.javabaseproject.ui.fragments.UserFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentsModule {
    @PerFragment
    @ContributesAndroidInjector(modules = {UserModule.class})
    abstract UserFragment bindUserFragment();
}
