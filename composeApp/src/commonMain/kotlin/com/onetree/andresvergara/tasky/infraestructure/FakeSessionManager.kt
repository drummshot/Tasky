package com.onetree.andresvergara.tasky.infraestructure

import com.onetree.andresvergara.tasky.domain.base.SessionManager
import com.onetree.andresvergara.tasky.domain.user.User
import com.onetree.andresvergara.tasky.domain.user.UserModel

class FakeSessionManager : SessionManager {

    private var user: User? = UserModel(
        id = 1,
        email = "andres.vergara@one-tree.com"
    )

    override fun setUser(user: User) {
        this.user = user
    }

    override fun getUser(): User {
        return user ?: throw IllegalStateException("User not set")
    }

    override fun clearSession() {
        user = null
    }
}