package com.onetree.andresvergara.tasky.presentation.task.state

import com.onetree.andresvergara.tasky.domain.task.Task

data class TaskUIState(
    override var id: Long = 0L,
    override var title: String = "",
    override var description: String? = null,
    override var completed: Boolean = false,
    override var latitude: Double? = null,
    override var longitude: Double? = null,
    override var userId: Long = 0,
    var isSelected: Boolean = false
) : Task {

    constructor(task: Task) : this(
        id = task.id,
        title = task.title,
        description = task.description,
        completed = task.completed,
        latitude = task.latitude,
        longitude = task.longitude,
        userId = task.userId,
        isSelected = false
    )
}