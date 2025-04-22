package com.onetree.andresvergara.tasky.app

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import tasky.composeapp.generated.resources.Res
import tasky.composeapp.generated.resources.new_task
import tasky.composeapp.generated.resources.my_tasks

@Composable
@Preview
fun App() {
    MaterialTheme {
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
}