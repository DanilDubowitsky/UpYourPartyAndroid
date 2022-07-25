package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.data.entities.room.user.UserEntity
import io.reactivex.rxjava3.core.Completable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(user: UserEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(user: List<UserEntity>): Completable

}