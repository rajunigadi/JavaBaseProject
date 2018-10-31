package com.raju.javabaseproject.di.modules.base;

import android.arch.persistence.room.Room;

import com.raju.javabaseproject.MyApp;
import com.raju.javabaseproject.R;
import com.raju.javabaseproject.data.source.local.AppDatabase;
import com.raju.javabaseproject.data.source.local.dao.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    AppDatabase providesRoomDatabase(MyApp app) {
        return Room
                .databaseBuilder(app, AppDatabase.class, app.getResources().getString(R.string.db_name))
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    UserDao providesUserDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }
}
