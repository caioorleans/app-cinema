package com.example.cinema.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cinema.R
import com.example.cinema.ui.theme.Red
import com.example.cinema.ui.theme.Secondary


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(modifier:Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize().padding( horizontal = 8.dp).padding(top = 16.dp)) {
                    LazyVerticalGridCinema()
        }
    }

@Composable
fun LazyVerticalGridCinema(){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
        modifier = Modifier.fillMaxSize().background(Secondary),

        content = {
            item { ItemCardMovie() }
            item { ItemCardMovie() }
            item { ItemCardMovie() }
            item { ItemCardMovie() }
            item { ItemCardMovie() }
        }
    )
}


@Composable
fun ItemCardMovie() {
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
           painter = painterResource(id = R.drawable.ic_launcher_background),
           contentDescription = "",

           )

    }
}