package com.onetree.andresvergara.tasky.data.task.datasource

import com.onetree.andresvergara.tasky.data.base.DataSource
import com.onetree.andresvergara.tasky.domain.task.Task

interface TaskDataSource : DataSource<Task> {
    suspend fun updateCompletion(ids: List<Long>, isCompleted: Boolean): List<Long>
    suspend fun list(userId: Long): List<Task>

    override suspend fun list(): List<Task> {
        throw NotImplementedError("list() not implemented yet")
    }
}