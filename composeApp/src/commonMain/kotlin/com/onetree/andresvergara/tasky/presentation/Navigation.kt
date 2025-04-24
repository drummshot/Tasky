package com.onetree.andresvergara.tasky.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.onetree.andresvergara.tasky.presentation.task.ui.TaskDetailScreen
import com.onetree.andresvergara.tasky.presentation.task.ui.TaskHomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable(route = "home") {
            TaskHomeScreen(
                onTaskClick = { task ->
                    navController.navigate("detail/${task.id}")
                }
            )
        }
        composable(
            route = "detail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.LongType })
        ) {
            val taskId = it.arguments?.getLong("taskId") ?: -1L
            TaskDetailScreen(
                taskId = taskId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}