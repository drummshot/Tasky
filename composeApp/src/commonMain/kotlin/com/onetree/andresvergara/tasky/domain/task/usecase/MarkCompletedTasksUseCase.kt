package com.onetree.andresvergara.tasky.domain.task.usecase

import com.onetree.andresvergara.tasky.domain.base.UseCase
import com.onetree.andresvergara.tasky.domain.task.Task

interface MarkCompletedTasksUseCase : UseCase<Task, List<Long>>