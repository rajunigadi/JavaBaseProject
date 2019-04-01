package com.raju.javabaseproject.dagger.modules.base

import android.app.Application
import androidx.room.Room

import com.raju.javabaseproject.R
import com.raju.javabaseproject.data.source.local.AppDatabase
import com.raju.javabaseproject.data.source.local.dao.UserDao

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Singleton
    @Provides
    internal fun providesRoomDatabase(application: Application): AppDatabase {
        return Room
                .databaseBuilder(application, AppDatabase::class.java, application.resources.getString(R.string.db_name))
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    internal fun providesHistoryDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

}
