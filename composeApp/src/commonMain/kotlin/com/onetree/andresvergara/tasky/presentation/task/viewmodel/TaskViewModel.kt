package com.onetree.andresvergara.tasky.presentation.task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onetree.andresvergara.tasky.domain.AppException
import com.onetree.andresvergara.tasky.domain.Params
import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.domain.task.TaskModel
import com.onetree.andresvergara.tasky.domain.task.usecase.CreateTaskUseCase
import com.onetree.andresvergara.tasky.domain.task.usecase.DeleteTasksUseCase
import com.onetree.andresvergara.tasky.domain.task.usecase.ListTasksUseCase
import com.onetree.andresvergara.tasky.domain.task.usecase.MarkCompletedTasksUseCase
import com.onetree.andresvergara.tasky.domain.task.usecase.TaskDetailUseCase
import com.onetree.andresvergara.tasky.presentation.base.UiEvent
import com.onetree.andresvergara.tasky.presentation.task.state.TaskListUIState
import com.onetree.andresvergara.tasky.presentation.task.state.TaskUIState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskDetail: TaskDetailUseCase,
    private val listTasks: ListTasksUseCase,
    private val createTask: CreateTaskUseCase,
    private val deleteTask: DeleteTasksUseCase,
    private val markCompletedTasks: MarkCompletedTasksUseCase,
) : ViewModel() {

    private var _loadListScreen = MutableStateFlow(false)

    private var _uiListState = MutableStateFlow(TaskListUIState())
    val uiListState: StateFlow<TaskListUIState> = _uiListState

    private var _uiDetailState = MutableStateFlow<TaskUIState>(TaskUIState())
    val uiDetailState: StateFlow<TaskUIState> = _uiDetailState

    private val _uiSnackEvent = MutableSharedFlow<UiEvent>()
    val uiSnackEvent: SharedFlow<UiEvent> = _uiSnackEvent

    fun getTasksById(taskId: Long) {
        viewModelScope.launch {
            val call = taskDetail.invoke(
                Params<Task>().apply {
                    id = taskId
                }
            )
            if (call.isSuccess) {
                call.getOrNull()?.let { task ->
                    _uiDetailState.update {
                        TaskUIState(task)
                    }
                }
            } else {
                //handle error
            }
        }
    }

    val loadScreen =
        _loadListScreen.onStart {
            loadTasks()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000L),
            initialValue = false,
        )

    private fun activateLoading() {
        _uiListState.update {
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
                _uiListState.update {
                    it.copy(
                        tasks = call.getOrNull()?.mapNotNull {
                            TaskUIState(it)
                        } ?: emptyList(),
                        isLoading = false,
                        errorCode = null
                    )
                }
            }

            call.isFailure -> {
                _uiListState.update {
                    it.copy(
                        isLoading = false,
                        errorCode = (call.exceptionOrNull() as? AppException)?.errorCode
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
                result.getOrNull()?.let { newTask ->
                    _uiListState.update {
                        it.copy(
                            tasks = it.tasks + TaskUIState(newTask)
                        )
                    }
                }
                _uiSnackEvent.tryEmit(UiEvent.ShowSnackbar(true))
            } else {
                // Error Handling
            }
        }
    }

    fun toggleSelection(state: TaskUIState) {
        _uiListState.update {
            it.copy(
                tasks = it.tasks.map {
                    if (it.id == state.id) {
                        it.copy(isSelected = !it.isSelected)
                    } else {
                        it
                    }
                }
            )
        }
    }

    fun deleteSelected() {
        viewModelScope.launch {
            val selectedTasks = _uiListState.value.tasks
                .filter { it.isSelected }
                .map { it.id }

            val call = deleteTask.invoke(
                Params<Task>().apply {
                    idsList = selectedTasks
                })
            if (call.isSuccess) {
                _uiListState.update {
                    it.copy(
                        tasks = it.tasks.filter { !selectedTasks.contains(it.id) }
                    )
                }
                _uiSnackEvent.tryEmit(UiEvent.ShowSnackbar(true))
            } else {
                // Error Handling
            }
        }

    }

    fun markAsComplete() {
        viewModelScope.launch {
            val selectedTasks = _uiListState.value.tasks
                .filter { it.isSelected }
                .map { it.id }

            val call = markCompletedTasks.invoke(
                Params<Task>().apply {
                    idsList = selectedTasks
                })
            if (call.isSuccess) {
                _uiListState.update {
                    it.copy(
                        tasks = it.tasks.map {
                            if (it.id in selectedTasks) {
                                it.copy(
                                    completed = true
                                )
                            } else {
                                it
                            }
                        }
                    )
                }
                _uiSnackEvent.tryEmit(UiEvent.ShowSnackbar(true))
            } else {
                // Error Handling
            }
        }
    }
}