package com.raju.javabaseproject.dagger.modules.base

import com.raju.javabaseproject.dagger.modules.HomeModule
import com.raju.javabaseproject.ui.fragments.UserFragment
import com.raju.javabaseproject.dagger.PerFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @PerFragment
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun bindUserFragment(): UserFragment
}
