package com.raju.javabaseproject.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.raju.javabaseproject.data.model.User;
import com.raju.javabaseproject.data.source.local.dao.UserDao;

@Database(entities = {User.class}, version = AppDatabase.VERSION)
public abstract class AppDatabase extends RoomDatabase {
    static final int VERSION = 1;
    public abstract UserDao userDao();
}