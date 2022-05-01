package com.example.movierow.widgets

import androidx.compose.material.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.movierow.models.Movie
import androidx.compose.ui.Modifier

@Composable
fun addFavorite(
    movie: Movie,
    isFav: Boolean,
    onSaveClick: (Movie) -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp)
    ) {


        when (isFav) {

            true -> Icon(imageVector = Icons.Outlined.Favorite,
                contentDescription="",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.clickable {
                    onSaveClick(movie)
                })

            false -> Icon(imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription="",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.clickable {
                    onSaveClick(movie)
                })
        }


    }
}