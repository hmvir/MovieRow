package com.example.movierow.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testapp_video2.navigation.MovieScreens
import com.example.testapp_video2.screens.detailscreen.DetailScreen
import com.example.testapp_video2.screens.favorites.FavoritesScreen
import com.example.testapp_video2.screens.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.value) {
        composable(MovieScreens.HomeScreen.value) { HomeScreen(navController = navController) }
        composable(
            route = "${MovieScreens.DetailScreen.value}/{movieId}",
            arguments = listOf(
                navArgument(name = "movieId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            DetailScreen( navController = navController, movieId = backStackEntry.arguments?.getString("movieId"))
        }
        composable(MovieScreens.FavoritesScreen.value) { FavoritesScreen(navController = navController) }
    }
}