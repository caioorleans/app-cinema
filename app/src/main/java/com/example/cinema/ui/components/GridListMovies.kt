package com.example.cinema.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cinema.R
import com.example.cinema.ui.data.model.Result
import com.example.cinema.ui.theme.Secondary
import com.example.cinema.ui.theme.White


@Composable
fun LazyVerticalGridMovies(listMovies: List<Result> = listOf(), navController: NavController,  showCloseButtonCards:Boolean = false){

    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Secondary),
    ){
        items(listMovies){
            ItemCardMovie(movie = it, navController)
        }
    }
}


@Composable
fun ItemCardMovie(movie:Result, navController: NavController, showCloseButton:Boolean = false) {
    if (showCloseButton)
        Row(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
                .offset(x = 10.dp, y = (-10).dp)
                .zIndex(1f),
            horizontalArrangement = Arrangement.End,

        ){
            IconButtonCinema(Icons.Filled.Info, "Menu", White){}
        }

    Box(
        modifier = Modifier
            .height(300.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { navController.navigate("details/${movie.id}") }
        ) {

//        Image(
//            painter = painterResource(id = R.drawable.poster_exemplo),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize(),
//        )

        val posterPath = movie.poster_path
        AsyncImage(
            model =
                ImageRequest.Builder(context = LocalContext.current)
                    .data("https://image.tmdb.org/t/p/original$posterPath")
                    .crossfade(true).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
    }
}