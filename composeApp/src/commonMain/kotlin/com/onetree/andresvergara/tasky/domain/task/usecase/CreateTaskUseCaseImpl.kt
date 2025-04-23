package com.onetree.andresvergara.tasky.domain.task.usecase

import com.onetree.andresvergara.tasky.domain.AppException.ValidationException
import com.onetree.andresvergara.tasky.domain.Error
import com.onetree.andresvergara.tasky.domain.Params
import com.onetree.andresvergara.tasky.domain.base.BaseUseCase
import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.domain.task.TaskRepo

class CreateTaskUseCaseImpl(
    private val taskRepo: TaskRepo
) : BaseUseCase<Task, Task>(), CreateTaskUseCase {

    override suspend fun execute(params: Params<Task>?): Result<Task> {
        val task = params?.item

        return if (params?.item != null)
            taskRepo.create(task)
        else Result.failure(
            exception = ValidationException(
                message = "Invalid Params",
                code = Error.INVALID_PARAMS
            )
        )
    }
}