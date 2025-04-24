package com.onetree.andresvergara.tasky.domain.task.usecase

import com.onetree.andresvergara.tasky.domain.AppException.ValidationException
import com.onetree.andresvergara.tasky.domain.ErrorCode
import com.onetree.andresvergara.tasky.domain.Params
import com.onetree.andresvergara.tasky.domain.base.BaseUseCase
import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.domain.task.TaskRepo

class TaskDetailUseCaseImpl(
    private val taskRepo: TaskRepo
) : BaseUseCase<Task, Task>(), TaskDetailUseCase {

    override suspend fun execute(params: Params<Task>?): Result<Task?> {
        val taskId = params?.id

        return if (taskId != null) {
            taskRepo.read(taskId)
        } else Result.failure(
            exception = ValidationException(
                message = "Invalid Params",
                errorCode = ErrorCode.INVALID_PARAMS
            )
        )
    }
}