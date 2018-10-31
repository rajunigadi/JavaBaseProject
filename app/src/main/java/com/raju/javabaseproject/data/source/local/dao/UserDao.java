package com.raju.javabaseproject.data.source.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.raju.javabaseproject.data.model.User;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(List<User> users);

    @Query("SELECT * FROM user")
    List<User> getUsers();

    @Query("SELECT * FROM user")
    Flowable<List<User>> getUsersAsFlowable();
}
