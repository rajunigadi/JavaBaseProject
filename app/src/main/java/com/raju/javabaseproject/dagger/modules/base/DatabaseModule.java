package com.raju.javabaseproject.dagger.modules.base;

import android.app.Application;
import android.arch.persistence.room.Room;

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
    AppDatabase providesRoomDatabase(Application application) {
        return Room
                .databaseBuilder(application, AppDatabase.class, application.getResources().getString(R.string.db_name))
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    UserDao providesHistoryDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }

}
