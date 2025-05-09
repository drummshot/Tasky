package com.onetree.andresvergara.tasky.domain.task

data class TaskModel(
    override var id: Long = 0L,
    override var title: String,
    override var description: String? = null,
    override var completed: Boolean = false,
    override var latitude: Double? = null,
    override var longitude: Double? = null,
    override var userId: Long = 0L
) : Task {

    constructor(task: Task) : this(
        id = task.id,
        title = task.title,
        description = task.description,
        completed = task.completed,
        latitude = task.latitude,
        longitude = task.longitude,
        userId = task.userId
    )

    override fun toString(): String {
        return "Task(title='$title', description=$description, completed=$completed, latitude=$latitude, longitude=$longitude, userId=$userId)"
    }
}
