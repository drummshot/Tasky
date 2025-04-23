package com.onetree.andresvergara.tasky.domain.base

import com.onetree.andresvergara.tasky.domain.task.Task

interface RepoBase<DO : DomainObject> {

    suspend fun create(item: DO): Result<DO>

    suspend fun read(id: Long): Result<DO?>

    suspend fun update(item: Task): Result<DO>

    suspend fun delete(id: Long): Result<Boolean>

    suspend fun list(): Result<List<DO>>
}