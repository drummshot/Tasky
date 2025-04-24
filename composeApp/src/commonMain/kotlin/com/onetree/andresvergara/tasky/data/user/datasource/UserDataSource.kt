package com.onetree.andresvergara.tasky.data.user.datasource

import com.onetree.andresvergara.tasky.data.base.DataSource
import com.onetree.andresvergara.tasky.domain.user.User

interface  UserDataSource : DataSource<User> {

    override suspend fun read(id: Long): User? {
        throw NotImplementedError("read() not implemented yet")
    }

    override suspend fun update(items: List<User>): List<User> {
        throw NotImplementedError("update() not implemented yet")
    }

    override suspend fun delete(ids: List<Long>): Boolean {
        throw NotImplementedError("delete() not implemented yet")
    }

    override suspend fun list(): List<User> {
        throw NotImplementedError("list() not implemented yet")
    }
}