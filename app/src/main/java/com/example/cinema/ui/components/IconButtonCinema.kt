package com.example.cinema.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.cinema.ui.theme.White

@Composable
fun IconButtonCinema (image: ImageVector,
                      description: String="",
                      colorText: Color=White,
                      colorBackground: Color=Color.Transparent,
                      size: Dp=34.dp,
                      onClick:()->Unit){
    IconButton(onClick = { onClick() },
        modifier = Modifier.clip(CircleShape).background(colorBackground).size(size)
    ) {
        Icon(image,
            contentDescription = description,
            tint = colorText,
            modifier = Modifier.size(34.dp)
        )
    }
}