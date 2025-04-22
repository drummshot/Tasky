package com.onetree.andresvergara.tasky.data

import com.onetree.andresvergara.tasky.domain.base.DomainObject

interface DataSource<B : DomainObject> {

    suspend fun create(item: B): Result<B>

    suspend fun read(id: String): Result<B>

    suspend fun update(item: B): Result<B>

    suspend fun delete(id: String): Result<Boolean>

    suspend fun list(): Result<List<B>>
}