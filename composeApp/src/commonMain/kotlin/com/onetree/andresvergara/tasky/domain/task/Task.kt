package com.onetree.andresvergara.tasky.domain.task

import com.onetree.andresvergara.tasky.domain.base.DomainObject

interface Task : DomainObject {
    val title: String
    val description: String?
    val completed: Boolean
    val latitude: Double?
    val longitude: Double?
}
