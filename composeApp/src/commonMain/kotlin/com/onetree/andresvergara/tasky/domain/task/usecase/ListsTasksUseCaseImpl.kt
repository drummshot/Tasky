package com.onetree.andresvergara.tasky.domain.task.usecase

import com.onetree.andresvergara.tasky.domain.Params
import com.onetree.andresvergara.tasky.domain.base.BaseUseCase
import com.onetree.andresvergara.tasky.domain.base.SessionManager
import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.domain.task.TaskRepo

class ListsTasksUseCaseImpl(
    private val taskRepo: TaskRepo,
    private val sessionManager: SessionManager
) : BaseUseCase<Task, List<Task>>(), ListTasksUseCase {

    override suspend fun execute(params: Params<Task>?): Result<List<Task>> {
        return taskRepo.listByUserId(
            sessionManager.getUser().id
        )
    }
}