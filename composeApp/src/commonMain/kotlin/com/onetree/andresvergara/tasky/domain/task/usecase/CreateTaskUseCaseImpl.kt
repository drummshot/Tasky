package com.onetree.andresvergara.tasky.domain.task.usecase

import com.onetree.andresvergara.tasky.domain.AppException.ValidationException
import com.onetree.andresvergara.tasky.domain.ErrorCode
import com.onetree.andresvergara.tasky.domain.Params
import com.onetree.andresvergara.tasky.domain.base.BaseUseCase
import com.onetree.andresvergara.tasky.domain.base.SessionManager
import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.domain.task.TaskRepo
import dev.jordond.compass.geolocation.Geolocator
import dev.jordond.compass.geolocation.currentLocationOrNull

class CreateTaskUseCaseImpl(
    private val taskRepo: TaskRepo,
    private val geoLocator: Geolocator,
    private val sessionManager: SessionManager
) : BaseUseCase<Task, Task>(), CreateTaskUseCase {

    private suspend fun updateTaskCurrentLocation(task: Task) {
        geoLocator.currentLocationOrNull()?.let { location ->
            task.latitude = location.coordinates.latitude
            task.longitude = location.coordinates.longitude
        }
    }

    override suspend fun execute(params: Params<Task>?): Result<Task?> {
        val task = params?.item


        return if (task != null) {
            task.userId = sessionManager.getUser().id
            updateTaskCurrentLocation(task)
            taskRepo.create(task)
        } else Result.failure(
            exception = ValidationException(
                message = "Invalid Params",
                errorCode = ErrorCode.INVALID_PARAMS
            )
        )
    }
}