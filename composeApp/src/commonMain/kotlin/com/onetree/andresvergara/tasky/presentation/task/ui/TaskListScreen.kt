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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.onetree.andresvergara.tasky.domain.task.Task
import com.onetree.andresvergara.tasky.presentation.task.viewmodel.TaskViewModel
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import tasky.composeapp.generated.resources.Res
import tasky.composeapp.generated.resources.my_tasks
import tasky.composeapp.generated.resources.new_task


@Composable
fun TaskListScreen(
    viewModel: TaskViewModel = koinViewModel()
) {
    //To fetch the screen state
    viewModel.loadScreen.collectAsStateWithLifecycle()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TaskListView(
        tasks = uiState.tasks

    )
}

@Composable
@Preview
fun TaskListView(
    tasks: List<Task>,
    onTaskClick: (Task) -> Unit = {},
    onAddTaskClick: () -> Unit = {},
    onCompletedTaskClick: (Task) -> Unit = {},
    onDeleteTaskClick: (Task) -> Unit = {}
) {
    Scaffold(
        topBar = {
            val topBarTitle = stringResource(Res.string.my_tasks)
            TopAppBar(title = { Text(topBarTitle) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {

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
    }
}