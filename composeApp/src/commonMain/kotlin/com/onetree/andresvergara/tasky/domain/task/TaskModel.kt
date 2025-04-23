package com.onetree.andresvergara.tasky.domain.task

data class TaskModel(
    override val id: Long,
    override val title: String,
    override val description: String?,
    override val completed: Boolean,
    override val latitude: Double?,
    override val longitude: Double?,
) : Task {
    constructor(task: Task) : this(
        id = task.id,
        title = task.title,
        description = task.description,
        completed = task.completed,
        latitude = task.latitude,
        longitude = task.longitude,
    )

    override fun toString(): String {
        return "Task(title='$title', description=$description, completed=$completed, latitude=$latitude, longitude=$longitude)"
    }
}
