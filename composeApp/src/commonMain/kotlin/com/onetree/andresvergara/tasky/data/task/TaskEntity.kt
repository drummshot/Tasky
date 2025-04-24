package com.onetree.andresvergara.tasky.data.task

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onetree.andresvergara.tasky.domain.task.Task

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    override var id: Long = 0L,
    override var title: String,
    override var description: String?,
    override var completed: Boolean,
    override var latitude: Double?,
    override var longitude: Double?,
    override var userId: Long
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
}
