package com.onetree.andresvergara.tasky.data.task.repo

import com.onetree.andresvergara.tasky.data.task.datasource.TaskDataSource
import com.onetree.andresvergara.tasky.domain.AppException.DataException
import com.onetree.andresvergara.tasky.domain.Error
import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.domain.task.TaskModel
import com.onetree.andresvergara.tasky.domain.task.TaskRepo

class TaskRepoImpl(
    private val datasource: TaskDataSource
) : TaskRepo {

    override suspend fun create(task: Task): Result<Task> {
        return try {
            val created = datasource.create(task)
            val mapped = TaskModel(created)
            return Result.success(mapped)
        } catch (e: Exception) {
            Result.failure(
                DataException(
                    message = "Error creating task [$task]",
                    code = Error.DATABASE_INSERTION_ERROR,
                    cause = e
                )
            )
        }
    }

    override suspend fun read(id: Long): Result<Task?> {
        return try {
            val task = datasource.read(id)
            Result.success(task)
        } catch (e: Exception) {
            Result.failure(
                DataException(
                    message = "Error reading task, id:[$id]",
                    code = Error.DATABASE_READING_ERROR,
                    cause = e
                )
            )
        }
    }

    override suspend fun update(task: Task): Result<Task> {
        return Result.success(datasource.update(task))
    }

    override suspend fun delete(id: Long): Result<Boolean> {
        return Result.success(datasource.delete(id))
    }

    override suspend fun list(): Result<List<Task>> {
        return try {
            val lists = datasource.list()
            return Result.success(lists)
        } catch (e: Exception) {
            Result.failure(
                DataException(
                    message = "Error listing tasks",
                    code = Error.DATABASE_READING_ERROR,
                    cause = e
                )
            )
        }
    }
}