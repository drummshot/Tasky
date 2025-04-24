package com.onetree.andresvergara.tasky.data.user.repo

import com.onetree.andresvergara.tasky.data.user.datasource.UserDataSource
import com.onetree.andresvergara.tasky.domain.AppException.DataException
import com.onetree.andresvergara.tasky.domain.ErrorCode
import com.onetree.andresvergara.tasky.domain.user.User
import com.onetree.andresvergara.tasky.domain.user.UserModel
import com.onetree.andresvergara.tasky.domain.user.UserRepo

class UserRepoImpl(
    private val datasource: UserDataSource
) : UserRepo {

    override suspend fun create(user: User): Result<User> {
        return try {
            val created = datasource.create(user)
            val mapped = UserModel(created)
            return Result.success(mapped)
        } catch (e: Exception) {
            Result.failure(
                DataException(
                    message = "Error creating user [$user]",
                    errorCode = ErrorCode.DATABASE_INSERTION,
                    cause = e
                )
            )
        }
    }
}