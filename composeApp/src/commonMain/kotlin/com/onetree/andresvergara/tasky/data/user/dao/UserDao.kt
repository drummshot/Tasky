package com.onetree.andresvergara.tasky.data.user.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.onetree.andresvergara.tasky.data.user.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insert(task: UserEntity): Long

    @Query("SELECT * FROM userentity WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getByEmail(email: String, password: String): UserEntity?

}