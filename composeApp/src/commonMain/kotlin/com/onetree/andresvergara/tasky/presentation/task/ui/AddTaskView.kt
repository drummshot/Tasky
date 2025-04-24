package com.onetree.andresvergara.tasky.presentation.task.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.onetree.andresvergara.tasky.presentation.task.viewmodel.TaskViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import tasky.composeapp.generated.resources.Res
import tasky.composeapp.generated.resources.create_task
import tasky.composeapp.generated.resources.required_title
import tasky.composeapp.generated.resources.save
import tasky.composeapp.generated.resources.task_title
import tasky.composeapp.generated.resources.task_description

@Composable
fun AddTaskView(
    viewModel: TaskViewModel = koinViewModel()
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isTitleError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(Res.string.create_task),
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
                if (isTitleError && it.isNotBlank()) isTitleError = false
            },
            label = { Text(stringResource(Res.string.task_title)) },
            isError = isTitleError,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        if (isTitleError) {
            Text(
                text = stringResource(Res.string.required_title),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { it -> description = it },
            label = { Text(stringResource(Res.string.task_description)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (title.isBlank()) {
                    isTitleError = true
                } else {
                    viewModel.saveTask(title, description)
                    title = ""
                    description = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(Res.string.save))
        }
    }
}