package com.onetree.andresvergara.tasky.data.task.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.onetree.andresvergara.tasky.data.task.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM TaskEntity")
    fun list(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)
}