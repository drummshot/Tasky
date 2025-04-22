package com.onetree.andresvergara.tasky.data.task.datasource

import com.onetree.andresvergara.tasky.data.task.TaskEntity
import com.onetree.andresvergara.tasky.data.task.dao.TaskDao
import com.onetree.andresvergara.tasky.domain.task.Task

class TaskLocalDataSource(
    private val taskDao: TaskDao
) : TaskDataSource {

    override suspend fun create(item: Task): Result<Task> {
        return try {
            val taskEntity = TaskEntity(item)
            taskDao.insert(taskEntity)
            Result.success(item)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun read(id: String): Result<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun update(item: Task): Result<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun list(): Result<List<Task>> {
        TODO("Not yet implemented")
    }

}