package com.onetree.andresvergara.tasky.data.task

import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.domain.task.repo.TaskRepo

class TaskRepoImpl(
    private val datasource: TaskDataSource
) : TaskRepo {

    override fun createTask(task: Task): Task {
        return datasource.create(task)
    }

    override fun readTask(id: String): Task {
        return datasource.read(id)
    }

    override fun updateTask(task: Task): Task {
        return datasource.update(task)
    }

    override fun deleteTask(id: String): Boolean {
        return datasource.delete(id)
    }

    override fun listTasks(): List<Task> {
        return datasource.list()
    }
}