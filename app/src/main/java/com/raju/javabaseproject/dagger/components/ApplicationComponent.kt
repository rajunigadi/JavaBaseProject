package com.raju.javabaseproject.dagger.components

import com.raju.javabaseproject.MyApp
import com.raju.javabaseproject.dagger.modules.base.ActivityBindingModule
import com.raju.javabaseproject.dagger.modules.base.ApplicationModule
import com.raju.javabaseproject.dagger.modules.base.DatabaseModule
import com.raju.javabaseproject.dagger.modules.base.FragmentBindingModule
import com.raju.javabaseproject.dagger.modules.base.ImageModule
import com.raju.javabaseproject.dagger.modules.base.NetworkModule

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ActivityBindingModule::class,
    FragmentBindingModule::class,
    NetworkModule::class,
    ImageModule::class,
    DatabaseModule::class])

interface ApplicationComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: MyApp): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MyApp)
}
