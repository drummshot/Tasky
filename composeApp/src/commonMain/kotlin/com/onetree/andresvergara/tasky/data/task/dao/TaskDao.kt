package com.onetree.andresvergara.tasky.data.task.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.onetree.andresvergara.tasky.data.task.TaskEntity

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: TaskEntity): Long

    @Delete
    suspend fun delete(task: TaskEntity): Int

    @Query("SELECT * FROM taskentity WHERE userId = :userId")
    suspend fun getAll(userId: Long): List<TaskEntity>

    @Query("DELETE FROM taskentity WHERE id IN (:ids)")
    suspend fun deleteByIds(ids: List<Long>): Int

    @Update
    suspend fun updateTasks(tasks: List<TaskEntity>): Int

    @Query("SELECT * FROM taskentity WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): TaskEntity?

    @Query("UPDATE taskentity SET completed = :isCompleted WHERE id IN (:ids)")
    suspend fun updateTasksCompletion(ids: List<Long>, isCompleted: Boolean): Int

}