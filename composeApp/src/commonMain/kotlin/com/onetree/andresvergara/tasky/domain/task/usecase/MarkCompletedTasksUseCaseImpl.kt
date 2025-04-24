package com.onetree.andresvergara.tasky.domain.task.usecase

import com.onetree.andresvergara.tasky.domain.AppException.ValidationException
import com.onetree.andresvergara.tasky.domain.ErrorCode
import com.onetree.andresvergara.tasky.domain.Params
import com.onetree.andresvergara.tasky.domain.base.BaseUseCase
import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.domain.task.TaskRepo

class MarkCompletedTasksUseCaseImpl(
    private val taskRepo: TaskRepo
) : BaseUseCase<Task, List<Long>>(), MarkCompletedTasksUseCase {

    override suspend fun execute(params: Params<Task>?): Result<List<Long>> {
        val tasksIds = params?.idsList
        return if (!tasksIds.isNullOrEmpty()) {
            taskRepo.updateCompletion(tasksIds, true)
        } else Result.failure(
            exception = ValidationException(
                message = "Invalid Params",
                errorCode = ErrorCode.INVALID_PARAMS
            )
        )
    }
}