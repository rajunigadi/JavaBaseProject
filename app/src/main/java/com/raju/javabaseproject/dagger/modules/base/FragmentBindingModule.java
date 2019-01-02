package com.raju.javabaseproject.dagger.modules.base;

import com.raju.javabaseproject.dagger.modules.HomeModule;
import com.raju.javabaseproject.ui.fragments.UserFragment;
import com.raju.javabaseproject.dagger.PerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {
    @PerFragment
    @ContributesAndroidInjector(modules = {HomeModule.class})
    abstract UserFragment bindHomeFragment();
}
