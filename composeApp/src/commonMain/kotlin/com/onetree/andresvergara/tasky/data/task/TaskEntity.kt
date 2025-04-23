package com.onetree.andresvergara.tasky.data.task

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onetree.andresvergara.tasky.domain.task.Task
import kotlin.uuid.ExperimentalUuidApi

@Entity
@OptIn(ExperimentalUuidApi::class)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0L,
    override val title: String,
    override val description: String?,
    override val completed: Boolean,
    override val latitude: Double?,
    override val longitude: Double?
) : Task {

    constructor(task: Task) : this(
        id = task.id,
        title = task.title,
        description = task.description,
        completed = task.completed,
        latitude = task.latitude,
        longitude = task.longitude
    )
}
