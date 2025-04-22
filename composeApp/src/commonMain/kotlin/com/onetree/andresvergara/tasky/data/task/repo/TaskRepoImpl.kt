package com.onetree.andresvergara.tasky.data.task.repo

import com.onetree.andresvergara.tasky.data.task.datasource.TaskDataSource
import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.domain.task.repo.TaskRepo

class TaskRepoImpl(
    private val datasource: TaskDataSource
) : TaskRepo {

    override suspend fun create(task: Task): Result<Task> {
        return datasource.create(task)
    }

    override suspend fun read(id: String): Result<Task> {
        return datasource.read(id)
    }

    override suspend fun update(task: Task): Result<Task> {
        return datasource.update(task)
    }

    override suspend fun delete(id: String): Result<Boolean> {
        return datasource.delete(id)
    }

    override suspend fun list(): Result<List<Task>> {
        return datasource.list()
    }
}