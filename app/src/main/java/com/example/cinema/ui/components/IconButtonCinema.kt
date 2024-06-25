package com.example.cinema.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cinema.ui.theme.White

@Composable
fun IconButtonCinema (image: ImageVector, description: String="", color: Color =White,  onClick:()->Unit){
    IconButton(onClick = { onClick()} ) {
        Icon(image, contentDescription = "description", tint = color)
    }
}