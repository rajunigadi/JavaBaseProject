package com.raju.javabaseproject.dagger.modules.base

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.gson.Gson
import com.raju.javabaseproject.MyApp
import com.raju.javabaseproject.R
import com.raju.javabaseproject.dagger.DatabaseInfo

import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    internal fun provideContext(application: MyApp): Context {
        return application.applicationContext
    }

    @Provides
    internal fun provideApplication(application: MyApp): Application {
        return application
    }

    @Provides
    @DatabaseInfo
    internal fun provideDatabaseName(application: MyApp): String {
        return application.resources.getString(R.string.db_name)
    }

    @Provides
    internal fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @DatabaseInfo
    internal fun provideDatabaseVersion(application: MyApp): Int? {
        return application.resources.getInteger(R.integer.db_version)
    }

    @Provides
    internal fun provideSharedPrefs(application: MyApp): SharedPreferences {
        return application.getSharedPreferences(application.resources.getString(R.string.db_name), Context.MODE_PRIVATE)
    }

    @Provides
    internal fun providesFirebaseAnalytics(application: MyApp): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(application)
    }
}
