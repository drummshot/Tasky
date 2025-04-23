package com.onetree.andresvergara.tasky.domain.task.usecase

import com.onetree.andresvergara.tasky.domain.Params
import com.onetree.andresvergara.tasky.domain.base.BaseUseCase
import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.domain.task.TaskRepo

class ListsTasksUseCaseImpl(
    private val taskRepo: TaskRepo
) : BaseUseCase<Task, List<Task>>(), ListTasksUseCase {

    override suspend fun execute(params: Params<Task>?): Result<List<Task>> = taskRepo.list()

}