package com.onetree.andresvergara.tasky.presentation.task.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.touchlab.kermit.Logger
import com.dokar.sheets.BottomSheetValue
import com.dokar.sheets.m3.BottomSheet
import com.dokar.sheets.rememberBottomSheetState
import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.presentation.task.viewmodel.TaskViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import tasky.composeapp.generated.resources.Res
import tasky.composeapp.generated.resources.my_tasks
import tasky.composeapp.generated.resources.new_task


@Composable
fun TaskHomeScreen(
    viewModel: TaskViewModel = koinViewModel()
) {
    //To fetch the screen state
    viewModel.loadScreen.collectAsStateWithLifecycle()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TaskHomeView(
        tasks = uiState.tasks
    )
}

@Composable
@Preview
fun TaskHomeView(
    tasks: List<Task>,
    onTaskClick: (Task) -> Unit = {},
    onAddTaskClick: () -> Unit = {},
    onCompletedTaskClick: (Task) -> Unit = {},
    onDeleteTaskClick: (Task) -> Unit = {}
) {

    val scope = rememberCoroutineScope()
    val state = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
    )


    Scaffold(
        topBar = {
            val topBarTitle = stringResource(Res.string.my_tasks)
            TopAppBar(title = { Text(topBarTitle) })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Logger.i { "Floating Button Clicked" }
                    scope.launch { state.expand() }
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

        }

        BottomSheet(
            state = state,
            showAboveKeyboard = true,
            skipPeeked = true,
        ) {
            AddTaskView()
        }
    }
}