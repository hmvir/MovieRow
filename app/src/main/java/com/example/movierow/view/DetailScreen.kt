package com.example.testapp_video2.screens.detailscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movierow.MovieRow
import com.example.movierow.models.Favorites
import com.example.movierow.models.Movie
import com.example.movierow.models.getMovieById
import com.example.movierow.widgets.HorizontalScrollableImageView
import com.example.movierow.widgets.addFavorite


@Composable
fun DetailScreen( navController: NavController = rememberNavController(), movieId : String?, favorites: Favorites) {
    val movie = getMovieById( movieId = movieId )

    Scaffold(topBar = {
        TopAppBar() {
            Row {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back",
                    modifier = Modifier.clickable {
                        navController.navigateUp()
                    }
                )

                Spacer(modifier = Modifier.width(20.dp))
                Text(text = movie.title)
            }
        }
    }) {

        MainContent(movie = movie, favorites)

    }
}

@Composable
fun MainContent( movie : Movie, favorites: Favorites) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
        Column() {
            MovieRow(movie = movie) {
                addFavorite(movie = movie, isFav = favorites.checkMovie(movie)) {
                    favorites.add(movie)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text(
                text = "Movie Images",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalScrollableImageView(movie = movie)
        }
    }
}
