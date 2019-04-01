package com.raju.javabaseproject.data.source.local.dao

import androidx.room.*
import com.raju.javabaseproject.data.model.User

import io.reactivex.Flowable

@Dao
interface UserDao {

    @get:Query("SELECT * FROM user ORDER BY id DESC")
    val users: Flowable<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * FROM user WHERE id = :id ORDER BY id DESC")
    fun getUser(id: String): User

    @Query("DELETE FROM user")
    fun deleteAll()
}