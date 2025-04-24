package com.onetree.andresvergara.tasky.data.user.datasource

import com.onetree.andresvergara.tasky.data.user.UserEntity
import com.onetree.andresvergara.tasky.data.user.dao.UserDao
import com.onetree.andresvergara.tasky.domain.user.User

class UserLocalDataSource(
    private val userDao: UserDao
) : UserDataSource {

    override suspend fun create(item: User): User {
        val userEntity = UserEntity(item)
        val result = userDao.insert(userEntity)
        return userEntity
    }
}