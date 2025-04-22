package com.onetree.andresvergara.tasky.domain.task.repo

import com.onetree.andresvergara.tasky.domain.task.Task

interface TaskRepo {

    fun createTask(task: Task): Task

    fun readTask(id: String): Task

    fun updateTask(task: Task): Task

    fun deleteTask(id: String): Boolean

    fun listTasks(): List<Task>
}