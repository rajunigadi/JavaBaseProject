package com.raju.javabaseproject.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.raju.javabaseproject.data.database.dao.UserDao;
import com.raju.javabaseproject.mvp.model.User;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

@Database(entities = {User.class}, version = AppDatabase.VERSION)
public abstract class AppDatabase extends RoomDatabase {
    static final int VERSION = 1;
    public abstract UserDao userDao();
}