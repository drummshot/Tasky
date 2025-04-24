package com.onetree.andresvergara.tasky.data.task.datasource

import com.onetree.andresvergara.tasky.data.task.TaskEntity
import com.onetree.andresvergara.tasky.data.task.dao.TaskDao
import com.onetree.andresvergara.tasky.domain.task.Task

class TaskLocalDataSource(
    private val taskDao: TaskDao
) : TaskDataSource {

    override suspend fun create(item: Task): Task {
        val taskEntity = TaskEntity(item)
        val result = taskDao.insert(taskEntity)
        taskEntity.id = result
        return taskEntity
    }

    override suspend fun read(id: Long): Task? {
        val taskEntity = taskDao.getById(id)
        return taskEntity
    }

    override suspend fun update(items: List<Task>): List<Task> {
        val entities = items.map { TaskEntity(it) }
        val result = taskDao.updateTasks(entities)
        return entities
    }

    override suspend fun delete(ids: List<Long>): Boolean {
        val result = taskDao.deleteByIds(ids)
        return result == ids.size
    }

    override suspend fun list(userId: Long): List<Task> {
        return taskDao.getAll(userId)
    }

    override suspend fun updateCompletion(
        ids: List<Long>,
        isCompleted: Boolean
    ): List<Long> {
        val result = taskDao.updateTasksCompletion(ids, isCompleted)
        return ids
    }
}