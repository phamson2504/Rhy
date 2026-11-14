package com.son.rhym.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.son.rhym.ui.create_habit.CreateHabitScreen
import com.son.rhym.ui.create_habit.repeat.RepeatScreen
import com.son.rhym.ui.create_task.CreateTaskScreen
import com.son.rhym.ui.home.RhymHomeScreen
import com.son.rhym.ui.setting.SettingScreen

object Screen {
    const val HOME = "home"
    const val STATISTICS = "statistics"
    const val SETTING = "setting"

    const val CREATE_FLOW = "createFlow?habitId={habitId}"
    fun createFlowRoute(habitId: Long = 0L) = "createFlow?habitId=$habitId"

    const val HABIT_FORM = "habitForm"
    const val REPEAT_SCREEN = "repeatScreen"

    const val CREATE_TASK = "createTask?taskId={taskId}"
    fun createTaskRoute(taskId: Long = 0L) = "createTask?taskId=$taskId"

    const val TASK_FORM = "taskForm"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = Screen.HOME, modifier = modifier) {

        composable(Screen.HOME) {
            RhymHomeScreen(
                onAddHabitClick = { navController.navigate(Screen.createFlowRoute()) },
                onAddTaskClick = { navController.navigate(Screen.createTaskRoute()) }
            )
        }
        composable(Screen.STATISTICS) { }
        composable(Screen.SETTING) { SettingScreen() }

        navigation(
            startDestination = Screen.HABIT_FORM,
            route = Screen.CREATE_FLOW,
            arguments = listOf(navArgument("habitId") {
                type = NavType.LongType
                defaultValue = 0L
            })
        ) {
            composable(Screen.HABIT_FORM) { entry ->
                val parent = remember(entry) { navController.getBackStackEntry(Screen.CREATE_FLOW) }
                CreateHabitScreen(
                    viewModel = hiltViewModel(parent),
                    onBackClick = { navController.popBackStack() },
                    onRepeatClick = { navController.navigate(Screen.REPEAT_SCREEN) },
                    onSaved = { navController.popBackStack(Screen.CREATE_FLOW, inclusive = true) }
                )
            }
            composable(Screen.REPEAT_SCREEN) { entry ->
                val parent = remember(entry) { navController.getBackStackEntry(Screen.CREATE_FLOW) }
                RepeatScreen(
                    viewModel = hiltViewModel(parent),
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        navigation(
            startDestination = Screen.TASK_FORM,
            route = Screen.CREATE_TASK,
            arguments = listOf(navArgument("taskId") {
                type = NavType.LongType
                defaultValue = 0L
            })
        ) {
            composable(Screen.TASK_FORM) { entry ->
                val parent = remember(entry) { navController.getBackStackEntry(Screen.CREATE_TASK) }
                CreateTaskScreen(
                    viewModel = hiltViewModel(parent),
                    onBackClick = { navController.popBackStack() },
                    onSaved = { navController.popBackStack(Screen.CREATE_TASK, inclusive = true) }
                )
            }
        }
    }
}
