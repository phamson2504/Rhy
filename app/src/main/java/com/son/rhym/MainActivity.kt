package com.son.rhym

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.son.rhym.ui.create_habit.CreateHabitScreen
import com.son.rhym.ui.home.RhymHomeScreen
import com.son.rhym.ui.theme.RhymTheme

class MainActivity : ComponentActivity() {
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
                    composable ("home") {
                        RhymHomeScreen(
                            onAddHabitClick = {
                                navController.navigate("addHabit")
                            }
                        )
                    }

                    composable("addHabit") {
                        CreateHabitScreen(
                            onBackClick = {
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
            onBackClick = {}
        )
    }
}