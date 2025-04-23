package com.onetree.andresvergara.tasky.data.base

import com.onetree.andresvergara.tasky.domain.base.DomainObject

interface DataSource<DO : DomainObject> {

    suspend fun create(item: DO): DO

    suspend fun read(id: Long): DO?

    suspend fun update(item: DO): DO

    suspend fun delete(id: Long): Boolean

    suspend fun list(): List<DO>
}