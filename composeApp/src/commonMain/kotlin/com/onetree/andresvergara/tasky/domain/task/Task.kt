package com.onetree.andresvergara.tasky.domain.task

import com.onetree.andresvergara.tasky.domain.base.DomainObject

interface Task : DomainObject {
    var title: String
    var description: String?
    var completed: Boolean
    var latitude: Double?
    var longitude: Double?
    var userId: Long
}
