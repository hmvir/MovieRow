package com.example.movierow.widgets

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movierow.models.Movie

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(
    movie : Movie,
    onClickItem: ( String ) -> Unit = {},
    favIcon: @Composable () -> Unit = {}) {

    var counter by remember {
        mutableStateOf(0)
    }
    var showHiddenInfo by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        //.height(if (showHiddenInfo) 250.dp else 130.dp)
        .clickable {
            onClickItem(movie.id)
            counter++
            //counter.value++
            Log.d("MainContent", "clicked: ${counter}")
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                //shape = RectangleShape,
                //elevation = 6.dp
            ) {
                //Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Profile picture")
                AsyncImage(
                    model = movie.images[0],
                    contentDescription = "Movie poster",
                    modifier = Modifier.clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
            }
            Icons.Default.FavoriteBorder
            Column() {
                Text(text = movie.title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(text = "Director: ${movie.director}", fontSize = 12.sp)
                Text(text = "Released: ${movie.year}", fontSize = 12.sp)
                AnimatedVisibility(
                    visible = showHiddenInfo
                ) {
                    Text(
                        text = "Plot: ${movie.plot}", fontSize = 12.sp,
                    )
                }
                AnimatedVisibility(
                    visible = showHiddenInfo) {
                    Divider(color = Color.LightGray, thickness = 1.dp,
                        modifier = Modifier.padding(2.dp)
                    )
                }
                AnimatedVisibility(
                    visible = showHiddenInfo
                ) {
                    Text(
                        text = "Genre: ${movie.genre}", fontSize = 12.sp,
                    )
                }
                AnimatedVisibility(
                    visible = showHiddenInfo
                ) {
                    Text(
                        text = "Actors: ${movie.actors}", fontSize = 12.sp,
                    )
                }
                AnimatedVisibility(
                    visible = showHiddenInfo
                ) {
                    Text(
                        text = "Rating: ${movie.rating}", fontSize = 12.sp,
                    )
                }
                if (showHiddenInfo)
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Hide additional information",
                        modifier = Modifier.clickable { showHiddenInfo = !showHiddenInfo }
                    )
                else
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Show hidden information",
                        modifier = Modifier.clickable { showHiddenInfo = !showHiddenInfo }
                    )
                favIcon()
            }
        }
    }
}

@Composable
fun HorizontalScrollableImageView( movie : Movie ) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier.padding(12.dp),
                elevation = 4.dp
            ) {
                AsyncImage(
                    //modifier = Modifier.clip(CircleShape),
                    //contentScale = ContentScale.Crop,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data( image )
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image number " + movie.images.indexOf(image) + " of " + movie.title
                )
            }
        }
    }
}

