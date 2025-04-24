package com.onetree.andresvergara.tasky.presentation.task.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.onetree.andresvergara.tasky.presentation.task.viewmodel.TaskViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import tasky.composeapp.generated.resources.Res
import tasky.composeapp.generated.resources.back
import tasky.composeapp.generated.resources.completed
import tasky.composeapp.generated.resources.pending
import tasky.composeapp.generated.resources.task_latitude
import tasky.composeapp.generated.resources.task_location
import tasky.composeapp.generated.resources.task_longitude


@Composable
fun TaskDetailScreen(
    taskId: Long,
    viewModel: TaskViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val task by viewModel.uiDetailState.collectAsStateWithLifecycle()
    viewModel.getTasksById(taskId)

    Scaffold(
        topBar = {
            TopAppBar(
                windowInsets = WindowInsets.systemBars,
                title = {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.h6.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(Res.string.back),
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),

            ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                //.verticalScroll(rememberScrollState())
            ) {

                Text(
                    text = task.description ?: "",
                    style = MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = if (task.completed)
                        stringResource(Res.string.completed)
                    else
                        stringResource(Res.string.pending),
                    style = MaterialTheme.typography.button,
                    color = if (task.completed)
                        MaterialTheme.colors.primary
                    else
                        MaterialTheme.colors.error
                )


                if (task.latitude != null && task.longitude != null) {

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = stringResource(Res.string.task_location),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = stringResource(Res.string.task_latitude) + task.latitude,
                        style = MaterialTheme.typography.caption
                    )
                    Text(
                        text = stringResource(Res.string.task_longitude) + task.longitude,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }

}