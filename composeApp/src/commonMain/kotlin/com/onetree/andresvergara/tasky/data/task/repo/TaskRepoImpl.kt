package com.onetree.andresvergara.tasky.data.task.repo

import com.onetree.andresvergara.tasky.data.task.datasource.TaskDataSource
import com.onetree.andresvergara.tasky.domain.AppException.DataException
import com.onetree.andresvergara.tasky.domain.ErrorCode
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
                    errorCode = ErrorCode.DATABASE_INSERTION,
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
                    errorCode = ErrorCode.DATABASE_READING,
                    cause = e
                )
            )
        }
    }

    override suspend fun update(tasks: List<Task>): Result<List<Task>> {
        return try {
            val tasks = datasource.update(tasks)
            Result.success(tasks)
        } catch (e: Exception) {
            Result.failure(
                DataException(
                    message = "Error updating tasks, ids:[${tasks.map { it.id }}]",
                    errorCode = ErrorCode.DATABASE_UPDATING,
                    cause = e
                )
            )
        }
    }

    override suspend fun delete(ids: List<Long>): Result<Boolean> {
        return try {
            val result = datasource.delete(ids)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(
                DataException(
                    message = "Error deleting tasks, ids:[${ids}]",
                    errorCode = ErrorCode.DATABASE_DELETING,
                    cause = e
                )
            )
        }
    }

    override suspend fun listByUserId(userId: Long): Result<List<Task>> {
        return try {
            val lists = datasource.list(userId)
            return Result.success(lists)
        } catch (e: Exception) {
            Result.failure(
                DataException(
                    message = "Error listing tasks",
                    errorCode = ErrorCode.DATABASE_READING,
                    cause = e
                )
            )
        }
    }

    override suspend fun updateCompletion(
        ids: List<Long>,
        isCompleted: Boolean
    ): Result<List<Long>> {
        return try {
            val tasks = datasource.updateCompletion(ids, isCompleted)
            Result.success(tasks)
        } catch (e: Exception) {
            Result.failure(
                DataException(
                    message = "Error updating tasks, ids:[$ids]",
                    errorCode = ErrorCode.DATABASE_UPDATING,
                    cause = e
                )
            )
        }
    }
}