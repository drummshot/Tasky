package com.onetree.andresvergara.tasky.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.onetree.andresvergara.tasky.presentation.task.ui.TaskDetailScreen
import com.onetree.andresvergara.tasky.presentation.task.ui.TaskHomeScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "task_list"
    ) {
        composable("task_list") {
            TaskHomeScreen()
        }
        composable("task_detail") {
            TaskDetailScreen()
        }
    }
}