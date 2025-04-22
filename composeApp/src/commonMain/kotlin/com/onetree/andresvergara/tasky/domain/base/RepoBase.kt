package com.onetree.andresvergara.tasky.domain.base

import com.onetree.andresvergara.tasky.domain.task.Task

interface RepoBase<B : DomainObject> {

    suspend fun create(task: B): Result<B>

    suspend fun read(id: String): Result<B>

    suspend fun update(task: Task): Result<B>

    suspend fun delete(id: String): Result<Boolean>

    suspend fun list(): Result<List<B>>
}