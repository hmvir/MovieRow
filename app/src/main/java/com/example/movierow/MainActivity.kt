package com.example.movierow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movierow.ui.theme.MovieRowTheme
import com.example.movierow.models.Movie
import com.example.movierow.models.getMovies
import com.example.movierow.nav.MovieNavigation
import com.example.movierow.view.MainContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieNavigation()
            /*
            MovieRowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent(movieList = getMovies(), )
                }
            }

             */
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}, favIcon: @Composable () -> Unit = {}) {

    var showDetails by remember{mutableStateOf(false)}

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    )

    {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            Surface(
                modifier = Modifier
                    .size(130.dp)
                    .padding(12.dp),
                elevation = 6.dp
            )
            {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "movie pic")
            }

            Column() {
                Text(text = "${movie.title}", style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}")
                Text(text = "Released: ${movie.year}")

                AnimatedVisibility(visible = showDetails, enter = fadeIn(), exit = slideOutHorizontally() + shrinkVertically() + fadeOut())
                {
                    MovieDetails(movie)
                }
                when(showDetails)
                {
                    false -> Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "arrow down", modifier = Modifier.clickable { showDetails =! showDetails  })
                    true -> Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "arrow up", modifier = Modifier.clickable { showDetails =! showDetails  })
                }

            }

        }

        favIcon()

    }
}

@Composable
fun MovieDetails(m: Movie){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(4.dp))
    {
        Column() {
            Text(text ="Plot: ${m.plot}", style= MaterialTheme.typography.body2)
            Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(top =2.dp, bottom = 2.dp))
            Text(text ="Genre: ${m.genre}", style= MaterialTheme.typography.body2)
            Text(text ="Actor: ${m.actors}", style= MaterialTheme.typography.body2)
            Text(text ="Rating: ${m.rating}", style= MaterialTheme.typography.body2)
        }

    }

}


/*
@Composable
fun MainContent(movieList: List<Movie>) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Movies")
                },
                actions = {
                    val expanded = remember { mutableStateOf(false) }

                    Box(
                        Modifier
                            .wrapContentSize(Alignment.TopEnd)
                    ) {
                        IconButton(onClick = {
                            expanded.value = true
                        }) {
                            Icon(
                                Icons.Filled.MoreVert,
                                contentDescription = "More Menu"
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = expanded.value,
                        onDismissRequest = { expanded.value = false },
                        modifier = Modifier.width(130.dp)
                    ) {
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                        }) {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Heart"
                            )
                            Text("Favorites")
                        }
                    }
                }
            )
        }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)) {

            LazyColumn {
                items(movieList) { movieTitle -> MovieRow(movieTitle)}
                        addFavorite(movie = movie, isFav = favorites.checkMovie(movie)) {
                            favorites.add(movie)

                    }
                }
            }
        }
    }
}

 */

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieRowTheme {
        MovieRow()
    }
}*/
