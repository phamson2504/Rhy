package com.son.rhym

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.son.rhym.domain.model.RepeatConfig
import com.son.rhym.ui.create_habit.CreateHabitScreen
import com.son.rhym.ui.create_habit.repeat.RepeatScreen
import com.son.rhym.ui.home.RhymHomeScreen
import com.son.rhym.ui.setting.SettingScreen
import com.son.rhym.ui.theme.RhymTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RhymTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        RhymHomeScreen(
                            onAddHabitClick = {
                                navController.navigate("addHabit")
                            },
                            onNavigateToSetting = {
                                navController.navigate("setting")
                            }
                        )
                    }

                    composable("setting") {
                        SettingScreen(
                            onNavigateToHome = {
                                navController.popBackStack("home", inclusive = false)
                            }
                        )
                    }

                    composable("addHabit") { backStackEntry ->
                        val repeatConfig = backStackEntry.savedStateHandle.get<RepeatConfig>("repeatConfig")
                        CreateHabitScreen(
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onRepeatClick = { currentRepeatConfig ->
                                navController.currentBackStackEntry?.savedStateHandle?.set("repeatConfig", currentRepeatConfig)
                                navController.navigate("repeatScreen")
                            },
                            repeatConfigFromNav = repeatConfig
                        )
                    }

                    composable("repeatScreen") {
                        val initialConfig = navController.previousBackStackEntry?.savedStateHandle?.get<RepeatConfig>("repeatConfig")
                        RepeatScreen(
                            initialConfig = initialConfig,
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onConfirm = { config ->
                                navController.previousBackStackEntry?.savedStateHandle?.set("repeatConfig", config)
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainActivityPreview() {
    RhymTheme {
        CreateHabitScreen(
            onBackClick = {},
            onRepeatClick ={}
        )
    }
}