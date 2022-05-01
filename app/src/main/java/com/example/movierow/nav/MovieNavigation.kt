package com.example.movierow.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movierow.models.Favorites
import com.example.movierow.models.favorites.FavoritesScreen
import com.example.movierow.navigation.MovieScreens
import com.example.movierow.view.HomeScreen
import com.example.testapp_video2.screens.detailscreen.DetailScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    val favs: Favorites = viewModel()

    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.value) {
        composable(MovieScreens.HomeScreen.value) { HomeScreen(navController = navController, favs) }
        composable(
            route = "${MovieScreens.DetailScreen.value}/{movieId}",
            arguments = listOf(
                navArgument(name = "movieId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            DetailScreen( navController = navController, movieId = backStackEntry.arguments?.getString("movieId"), favs)
        }
        composable(MovieScreens.FavoritesScreen.value) { FavoritesScreen(navController = navController, favs) }
    }
}