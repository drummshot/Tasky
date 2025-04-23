package com.onetree.andresvergara.tasky.data.task.datasource

import com.onetree.andresvergara.tasky.data.task.TaskEntity
import com.onetree.andresvergara.tasky.data.task.dao.TaskDao
import com.onetree.andresvergara.tasky.domain.task.Task

class TaskLocalDataSource(
    private val taskDao: TaskDao
) : TaskDataSource {

    override suspend fun create(item: Task): Task {
        val taskEntity = TaskEntity(item)
        taskDao.insert(taskEntity)
        return taskEntity
    }

    override suspend fun read(id: Long): Task? {
        TODO("Not yet implemented")
    }

    override suspend fun update(item: Task): Task {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun list(): List<Task> {
        return taskDao.getAll()
    }

}