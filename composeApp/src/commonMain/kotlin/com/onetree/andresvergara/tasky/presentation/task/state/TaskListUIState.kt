package com.onetree.andresvergara.tasky.presentation.task.state

import com.onetree.andresvergara.tasky.domain.Error
import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.presentation.base.UIStateBase

data class TaskListUIState(
    val tasks: List<Task> = emptyList(),
    override val isLoading: Boolean = false,
    override val errorCode: Error? = null,
) : UIStateBase