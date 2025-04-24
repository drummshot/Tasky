package com.onetree.andresvergara.tasky.domain.user

data class UserModel(
    override var id: Long = 0L,
    override var email: String,
    override var password: String? = null
) : User {

    constructor(task: User) : this(
        id = task.id,
        email = task.email,
        password = task.password,
    )
}
