package com.onetree.andresvergara.tasky.presentation.task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onetree.andresvergara.tasky.domain.AppException
import com.onetree.andresvergara.tasky.domain.Params
import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.domain.task.TaskModel
import com.onetree.andresvergara.tasky.domain.task.usecase.CreateTaskUseCase
import com.onetree.andresvergara.tasky.domain.task.usecase.ListTasksUseCase
import com.onetree.andresvergara.tasky.presentation.task.state.TaskListUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(
    private val listTasks: ListTasksUseCase,
    private val createTask: CreateTaskUseCase
) : ViewModel() {

    private var _loadListScreen = MutableStateFlow(false)

    private var _uiState = MutableStateFlow(TaskListUIState())
    val uiState: StateFlow<TaskListUIState> = _uiState

    val loadScreen =
        _loadListScreen.onStart {
            loadTasks()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000L),
            initialValue = false,
        )

    private fun activateLoading() {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }
    }

    private suspend fun loadTasks() {
        activateLoading()
        val call = listTasks.invoke()
        when {
            call.isSuccess -> {
                _uiState.update {
                    it.copy(
                        tasks = call.getOrNull() ?: emptyList(),
                        isLoading = false,
                        errorCode = null
                    )
                }
            }

            call.isFailure -> {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorCode = (call.exceptionOrNull() as? AppException)?.code
                    )
                }

            }
        }
    }

    fun saveTask(title: String, description: String) {
        viewModelScope.launch {
            val result = createTask.invoke(
                Params<Task>().apply {
                    item = TaskModel(
                        title = title,
                        description = description
                    )
                }
            )
            if (result.isSuccess) {
                loadTasks()
            } else {
                // Error Handling
            }
        }
    }
}