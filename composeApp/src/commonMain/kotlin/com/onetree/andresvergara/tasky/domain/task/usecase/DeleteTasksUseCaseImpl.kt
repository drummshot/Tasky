package com.onetree.andresvergara.tasky.domain.task.usecase

import com.onetree.andresvergara.tasky.domain.AppException.UnknownException
import com.onetree.andresvergara.tasky.domain.AppException.ValidationException
import com.onetree.andresvergara.tasky.domain.ErrorCode
import com.onetree.andresvergara.tasky.domain.Params
import com.onetree.andresvergara.tasky.domain.base.BaseUseCase
import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.domain.task.TaskRepo

class DeleteTasksUseCaseImpl(
    private val taskRepo: TaskRepo
) : BaseUseCase<Task, List<Long>>(), DeleteTasksUseCase {

    override suspend fun execute(params: Params<Task>?): Result<List<Long>> {
        val idList = params?.idsList ?: emptyList()

        return if (idList.isNotEmpty()) {
            val result = taskRepo.delete(idList)
            if (result.isSuccess) {
                Result.success(idList)
            } else {
                Result.failure(
                    exception = result.exceptionOrNull() ?: UnknownException("")
                )
            }
        } else Result.failure(
            exception = ValidationException(
                message = "Invalid Params",
                errorCode = ErrorCode.INVALID_PARAMS
            )
        )
    }
}