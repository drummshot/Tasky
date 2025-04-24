package com.onetree.andresvergara.tasky.domain.base

interface RepoBase<DO : DomainObject> {

    suspend fun create(item: DO): Result<DO>

    suspend fun read(id: Long): Result<DO?>

    suspend fun update(items: List<DO>): Result<List<DO>>

    suspend fun delete(ids: List<Long>): Result<Boolean>

    suspend fun list(): Result<List<DO>>
}