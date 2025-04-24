package com.onetree.andresvergara.tasky.domain.user

import com.onetree.andresvergara.tasky.domain.AppException.NotImplementedException
import com.onetree.andresvergara.tasky.domain.base.RepoBase

interface UserRepo : RepoBase<User> {
    override suspend fun read(id: Long): Result<User?> {
        return Result.failure(NotImplementedException())
    }

    override suspend fun update(items: List<User>): Result<List<User>> {
        return Result.failure(NotImplementedException())
    }

    override suspend fun delete(ids: List<Long>): Result<Boolean> {
        return Result.failure(NotImplementedException())
    }

    override suspend fun list(): Result<List<User>> {
        return Result.failure(NotImplementedException())
    }
}