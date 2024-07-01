package com.example.cinema.ui.components

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
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cinema.ui.data.model.MediaResult
import com.example.cinema.ui.screens.home.MediaViewModel
import com.example.cinema.ui.theme.Primary
import com.example.cinema.ui.theme.Secondary
import com.example.cinema.ui.theme.White


@Composable
fun LazyVerticalGridMedia(
    listAllMedia: List<MediaResult> = listOf(),
    navController: NavController,
    showCloseButtonCards:Boolean = false,
    nextPage:()->Int
    ){
    val mediaViewModel:MediaViewModel = viewModel<MediaViewModel>()


    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Secondary),
    ){
        items(listAllMedia){
            ItemCardMovie(media = it, navController, showCloseButtonCards)
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            BottomBar(mediaViewModel, nextPage)
        }

    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemCardMovie(media:MediaResult, navController: NavController, showCloseButton:Boolean = false) {
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
            .clickable { navController.navigate("details/${media.id}") }
        ) {

        val posterPath = media.posterPath

        //implementation("com.github.bumptech.glide:compose:1.0.0-beta01")
        GlideImage(
            model = "https://image.tmdb.org/t/p/original$posterPath",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        ) {
            it.diskCacheStrategy(DiskCacheStrategy.ALL)
        }


    }
}

@Composable
fun BottomBar(moviesViewModel: MediaViewModel, nextPage:()->Int){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                moviesViewModel.getNextPageMedia(nextPage())
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = White
            )
        ) {
            Text(
                text = "Carregar",
                fontSize = 20.sp,
                color = Primary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}