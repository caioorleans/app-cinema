package com.example.cinema.ui.screens.details

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinema.R

@Composable
fun DetailsScreen(modifier: Modifier = Modifier){
    Surface(
        modifier.fillMaxSize()
    ) {
        Box(modifier = modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = R.drawable.poster_exemplo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxSize()
            )
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color(0, 0, 0, 255),
                                Color(0, 0, 0, 100)
                            ),
                            startX = 20F
                        )
                    )
            )
            Column(
                modifier
                    .align(Alignment.CenterStart)
                    .padding(horizontal = 40.dp)
            ) {
                Text(
                    text = "Kingdom of the planet of the apes",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = modifier.padding(bottom = 8.dp)
                )
                Row(
                    modifier = modifier.padding(bottom = 16.dp)
                ) {
                    val rowModifier = Modifier.padding(end = 10.dp)
                    Text(
                        text = "2024",
                        color = Color.LightGray,
                        modifier = rowModifier
                    )
                    Text(
                        text = "2866",
                        color = Color.Green,
                        modifier = rowModifier
                    )
                    Text(
                        text = "145 min",
                        color = Color.LightGray
                    )
                }
                Text(
                    text = "No one can stop the reign.",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Many years after the reign of Caesar, a young ape goes on a journey that will lead him to question everything he's been taught about the past and make choices that will define a future for apes and humans alike.",
                    color = Color.White,
                    modifier = modifier.padding(bottom = 16.dp)
                )
                Row {
                    val shape = RoundedCornerShape(10.dp)
                    Button(
                        onClick = { /*TODO*/ },
                        shape = shape,
                        colors = ButtonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White,
                            disabledContentColor = Color.DarkGray,
                            disabledContainerColor = Color.Gray
                        ),
                        modifier = modifier.padding(end = 10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "Play icon",
                            modifier = modifier.padding(end = 4.dp)
                        )
                        Text(text = "Trailer")
                    }
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        shape = shape,
                        border = BorderStroke(1.dp, Color.White)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Like icon",
                            tint = Color.White,
                            modifier = modifier.padding(end = 4.dp)
                        )
                        Text(
                            text = "Salvar",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun DetailsScreenPreview(){
    Scaffold {
        DetailsScreen(Modifier.padding(it))
    }
}