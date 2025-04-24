package com.onetree.andresvergara.tasky.presentation.task.state

import com.onetree.andresvergara.tasky.domain.ErrorCode
import com.onetree.andresvergara.tasky.presentation.base.UIStateBase

data class TaskListUIState(
    val tasks: List<TaskUIState> = emptyList(),
    override val isLoading: Boolean = false,
    override val errorCode: ErrorCode? = null,
    override val successOperation: Boolean = false
) : UIStateBase