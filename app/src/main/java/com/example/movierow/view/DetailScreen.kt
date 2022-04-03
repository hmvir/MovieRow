package com.example.testapp_video2.screens.detailscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movierow.MovieRow
import com.example.movierow.models.Movie
import com.example.movierow.models.getMovieById
import com.example.movierow.models.getMovies
import com.example.movierow.widgets.HorizontalScrollableImageView


@Composable
fun DetailScreen( navController: NavController = rememberNavController(), movieId : String? = getMovies()[0].id) {
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

        MainContent(movie = movie)

    }
}

@Composable
fun MainContent( movie : Movie) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
        Column() {
            MovieRow(movie = movie)

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
