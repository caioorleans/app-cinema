package com.example.cinema.ui.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema.R
import com.example.cinema.ui.components.LazyVerticalGridMedia
import com.example.cinema.ui.theme.Secondary
import com.example.cinema.ui.theme.White

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {
    val listMovies = listOf(
        R.drawable.ic_launcher_background,
    )
    Surface(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 8.dp)
        .padding(top = 16.dp)) {

        Column {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Secondary)
                .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
                ) {
                Text(text = "Minha Lista", fontSize = 30.sp, color = White)
            }

            //LazyVerticalGridMedia(listMovies, true)


        }

    }
}