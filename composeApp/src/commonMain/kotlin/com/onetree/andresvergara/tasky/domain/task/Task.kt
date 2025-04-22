package com.onetree.andresvergara.tasky.domain.task

interface Task : BusinessObject {
    val title: String
    val description: String?
    val completed: Boolean
    val latitude: Double?
    val longitude: Double?
}
