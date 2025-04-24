package com.onetree.andresvergara.tasky.presentation.task.ui


import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.touchlab.kermit.Logger
import com.onetree.andresvergara.tasky.presentation.base.UiEvent
import com.onetree.andresvergara.tasky.presentation.task.state.TaskListUIState
import com.onetree.andresvergara.tasky.presentation.task.state.TaskUIState
import com.onetree.andresvergara.tasky.presentation.task.viewmodel.TaskViewModel
import com.onetree.andresvergara.tasky.presentation.ui.kit.ModalBottomSheet
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import tasky.composeapp.generated.resources.Res
import tasky.composeapp.generated.resources.complete
import tasky.composeapp.generated.resources.delete
import tasky.composeapp.generated.resources.my_tasks
import tasky.composeapp.generated.resources.new_task


@Composable
fun TaskHomeScreen(
    viewModel: TaskViewModel = koinViewModel(),
    onTaskClick: (TaskUIState) -> Unit = {},
) {
    //To fetch the screen state
    viewModel.loadScreen.collectAsStateWithLifecycle()

    val uiState by viewModel.uiListState.collectAsStateWithLifecycle()
    val snackBarState by viewModel.uiSnackEvent.collectAsStateWithLifecycle(
        initialValue = UiEvent.IDLE()
    )

    TaskHomeView(
        uiState = uiState,
        snackBarState = snackBarState,
        onTaskClick = { task ->
            onTaskClick(task)
        },
        onToggleSelection = { task ->
            viewModel.toggleSelection(task)
        },
        onDeleteClick = {
            viewModel.deleteSelected()
        },
        onMarkAsComplete = {
            viewModel.markAsComplete()
        }

    )
}

@Composable
@Preview
fun TaskHomeView(
    uiState: TaskListUIState,
    snackBarState: UiEvent,
    onTaskClick: (TaskUIState) -> Unit = {},
    onToggleSelection: (TaskUIState) -> Unit = {},
    onDeleteClick: () -> Unit = {},
    onMarkAsComplete: () -> Unit = {}
) {
    val taskSelectedCount = uiState.tasks.count { it.isSelected }
    val snackbarHostState = remember { SnackbarHostState() }
    val genericSuccessMessage = stringResource(Res.string.my_tasks)
    val scope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    LaunchedEffect(Unit) {
        if (snackBarState is UiEvent.ShowSnackbar) {
            snackbarHostState.showSnackbar(
                message = genericSuccessMessage
            )
        }
    }

    Scaffold(
        topBar = {
            val topBarTitle = stringResource(Res.string.my_tasks)
            TopAppBar(
                windowInsets = WindowInsets.systemBars,
                title = {
                    Text(
                        text = topBarTitle,
                        style = MaterialTheme.typography.h6.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },

                actions = {

                    if (taskSelectedCount > 0) {

                        IconButton(
                            onClick = {
                                onMarkAsComplete()
                            }) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = stringResource(Res.string.complete),
                                tint = Color.White
                            )
                        }
                        IconButton(
                            onClick = {
                                onDeleteClick()
                            }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = stringResource(Res.string.delete),
                                tint = Color.White
                            )
                        }
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Logger.i { "Floating Button Clicked" }
                    scope.launch { sheetState.show() }
                }) {
                val newTask = stringResource(Res.string.new_task)
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = newTask
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            itemsIndexed(
                items = uiState.tasks,
                key = { _, task -> task.id }) { _, task ->
                TaskCard(
                    task = task,
                    toggleSelection = { task ->
                        onToggleSelection(task)
                    },
                    onTaskClick = { task ->
                        onTaskClick(task)
                    }
                )
            }
        }

        ModalBottomSheet(
            showSheet = sheetState,
            content = { AddTaskView() }
        )
    }
}