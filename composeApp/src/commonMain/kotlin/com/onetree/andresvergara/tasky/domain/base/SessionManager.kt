package com.onetree.andresvergara.tasky.domain.base

import com.onetree.andresvergara.tasky.domain.user.User

interface SessionManager {
    fun setUser(user: User)
    fun getUser(): User
    fun clearSession()
}