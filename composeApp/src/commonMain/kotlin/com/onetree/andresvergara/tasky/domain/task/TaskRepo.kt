package com.onetree.andresvergara.tasky.domain.task

import com.onetree.andresvergara.tasky.domain.AppException.NotImplementedException
import com.onetree.andresvergara.tasky.domain.base.RepoBase

interface TaskRepo : RepoBase<Task> {
    suspend fun updateCompletion(ids: List<Long>, isCompleted: Boolean): Result<List<Long>>
    suspend fun listByUserId(id: Long): Result<List<Task>>
    override suspend fun list(): Result<List<Task>> {
        return Result.failure(NotImplementedException())
    }
}