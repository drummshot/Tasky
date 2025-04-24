package com.onetree.andresvergara.tasky.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onetree.andresvergara.tasky.domain.user.User

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    override var id: Long = 0,
    override var email: String,
    override var password: String?
) : User {

    constructor(user: User) : this(
        id = user.id,
        email = user.email,
        password = user.password
    )
}