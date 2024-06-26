package com.example.cinema.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.cinema.ui.theme.Red
import com.example.cinema.ui.theme.Secondary
import com.example.cinema.ui.theme.White


@Composable
fun LazyVerticalGridMovies(listMovies: List<Int> = listOf(),  showCloseButtonCards:Boolean = false){

    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Secondary),

        content = {
            items(listMovies.size) { index ->
                ItemCardMovie(listMovies[index], showCloseButtonCards)
            }
        }
    )
}


@Composable
fun ItemCardMovie(imageSrc: Int, showCloseButton:Boolean = false) {
    if (showCloseButton)
        Row(
            Modifier.fillMaxWidth().height(60.dp).offset(x= 10.dp, y=(-10).dp).zIndex(1f),
            horizontalArrangement = Arrangement.End,

        ){
            IconButtonCinema(Icons.Filled.Info, "Menu", White){}
        }

    Box(
        modifier = Modifier
            .padding(6.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(16.dp))
        ) {

        Image(
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = imageSrc),
            contentDescription = "",

            )
    }
}