package com.raju.javabaseproject.data.source.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.raju.javabaseproject.data.model.User;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM user ORDER BY id DESC")
    Flowable<List<User>> getUsers();

    @Query("SELECT * FROM user WHERE id = :id ORDER BY id DESC")
    User getUser(String id);

    @Query("DELETE FROM user")
    void deleteAll();
}