package com.raju.javabaseproject.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raju.javabaseproject.data.model.User
import com.raju.javabaseproject.data.source.local.dao.UserDao

@Database(entities = [User::class], version = AppDatabase.VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        const val VERSION = 1
    }
}