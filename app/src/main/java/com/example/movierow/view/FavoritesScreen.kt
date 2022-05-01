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
import com.example.movierow.navigation.MovieScreens
import com.example.movierow.widgets.MovieRow

@Composable
fun FavoritesScreen(navController: NavController, favorites: Favorites) {
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
                        navController.navigate(route = "${MovieScreens.DetailScreen.value}/$movieId")
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
                        text = "No Favorites selected",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}