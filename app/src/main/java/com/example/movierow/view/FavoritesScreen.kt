package com.example.movierow.models.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movierow.models.Favorites
import com.example.movierow.models.Movie
import com.example.movierow.models.getMovies
import com.example.movierow.navigation.MovieScreens
import com.example.movierow.widgets.MovieRow
import com.example.movierow.widgets.addFavorite

@Composable
fun FavoritesScreen(navController: NavController = rememberNavController(), favorites: Favorites) {
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
                Text(text = "My Favorite Movies")
            }
        }
    })

    {

        val movieList = favorites.favs
        if (movieList.isEmpty() == false) {
            LazyColumn() {
                items(movieList) { movie ->
                    MovieRow(movie, { movieId ->
                        navController.navigate(route = "${MovieScreens.DetailScreen}/$movieId")
                    })
                }
            }
        } else {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 20.dp, end = 20.dp)
                    .height(200.dp),
                backgroundColor = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(corner = CornerSize(16.dp)),

                ) {
                Box(Modifier.wrapContentSize(Alignment.Center)) {
                    Text(
                        text = "No Favourites selected",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

@Composable
fun MainContent(navController: NavController = rememberNavController(), favorites: Favorites) {
    val movies = listOf<Movie>(getMovies()[1], getMovies()[2], getMovies()[3], getMovies()[4])
    Surface(
        color = MaterialTheme.colors.background
    ) {
        LazyColumn{

            items(items = movies) { movie ->
                MovieRow(movie = movie, onClickItem = { movieId ->
                    navController.navigate(
                        "${MovieScreens.DetailScreen.value}/$movieId"
                    )
                }, favIcon = {
                    addFavorite(
                        movie = movie,
                        isFav = favorites.checkMovie(movie),
                        onSaveClick = { favorites.add(movie) })
                }
                )
            }
        }
    }
}