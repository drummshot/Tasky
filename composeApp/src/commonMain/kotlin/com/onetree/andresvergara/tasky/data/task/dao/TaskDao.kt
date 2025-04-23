package com.onetree.andresvergara.tasky.data.task.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.onetree.andresvergara.tasky.data.task.TaskEntity

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)

    @Query("SELECT * FROM taskentity")
    suspend fun getAll(): List<TaskEntity>

}