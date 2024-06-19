package com.example.finalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalapp.featureNote.presentation.addEditNote.AddEditItemScreen
import com.example.finalapp.featureNote.presentation.items.ItemsScreen
import com.example.finalapp.featureNote.presentation.util.Screen
import com.example.finalapp.ui.theme.FinalAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.ItemsScreen.route
                    ) {
                        composable(route = Screen.ItemsScreen.route) {
                             ItemsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AddEditItemScreen.route +
                                    "?itemId={itemId}&itemColor={itemColor}",
                            arguments = listOf(
                                navArgument("itemId") {
                                    type = NavType.LongType
                                    defaultValue = -1L
                                },
                                navArgument("itemColor") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val color = it.arguments?.getInt("itemColor") ?: -1
                             AddEditItemScreen(
                                 navController = navController,
                                 itemColor = color
                             )
                        }
                    }
                }
            }
        }
    }
}