package com.example.cinema.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cinema.ui.theme.Secondary


@Composable
fun LazyVerticalGridMovies(listMovies: List<Int> = listOf()){

    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = Modifier.fillMaxSize().background(Secondary),

        content = {
            items(listMovies.size) { index ->
                ItemCardMovie(listMovies[index])
            }
        }
    )
}


@Composable
fun ItemCardMovie(imageSrc: Int) {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(16.dp))
        ,

        ) {
        Image(
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = imageSrc),
            contentDescription = "",

            )

    }
}