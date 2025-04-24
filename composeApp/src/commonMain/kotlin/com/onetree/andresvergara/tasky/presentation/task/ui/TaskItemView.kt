package com.onetree.andresvergara.tasky.presentation.task.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.onetree.andresvergara.tasky.presentation.task.state.TaskUIState
import org.jetbrains.compose.resources.stringResource
import tasky.composeapp.generated.resources.Res
import tasky.composeapp.generated.resources.completed
import tasky.composeapp.generated.resources.pending

@Composable
fun TaskCard(
    task: TaskUIState,
    modifier: Modifier = Modifier,
    toggleSelection: (TaskUIState) -> Unit,
    onTaskClick: (TaskUIState) -> Unit
) {

    val backgroundColor by animateColorAsState(
        targetValue = if (task.isSelected) Color(MaterialTheme.colors.secondary.value) else Color.LightGray,
        animationSpec = tween(durationMillis = 300)
    )

    val scale by animateFloatAsState(
        targetValue = if (task.isSelected) 1.2f else 1.0f,
        animationSpec = tween(durationMillis = 300)
    )

    val elevation by animateDpAsState(
        targetValue = if (task.isSelected) 8.dp else 2.dp,
        animationSpec = tween(durationMillis = 300)
    )


    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),

        ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            toggleSelection(task)
                        },
                        onTap = {
                            onTaskClick(task)
                        }
                    )
                }

        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        shadowElevation = elevation.toPx()
                        shape = CircleShape
                        clip = true
                    }
                    .clickable {
                        toggleSelection(task)
                    }
                    .background(backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = task.title.take(1).uppercase(),
                    style = MaterialTheme.typography.h5,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.subtitle1
                )

                task.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body1,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Row(
                    modifier = Modifier.align(
                        Alignment.End
                    )
                ) {
                    Text(
                        text = if (task.completed)
                            stringResource(Res.string.completed)
                        else stringResource(
                            Res.string.pending
                        ),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .padding(end = 6.dp)
                            .background(
                                color  = if (task.completed)
                                    MaterialTheme.colors.primary
                                else
                                    MaterialTheme.colors.error,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)

                    )
                }
            }
        }
    }
}