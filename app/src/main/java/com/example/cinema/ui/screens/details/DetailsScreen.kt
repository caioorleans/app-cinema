package com.example.cinema.ui.screens.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cinema.ui.data.model.MovieDetails

@Composable
fun DetailsScreen(movieUiState:MovieDetailsUiState, modifier: Modifier = Modifier){

    when(movieUiState){
        is MovieDetailsUiState.Success -> DetailsBody(movieUiState.result)
        is MovieDetailsUiState.Loading -> {
            Text(text = "Carregando")}
        is MovieDetailsUiState.Error -> {
            Text(text = "Ops, ocorreu um erro")
        }
    }

}

@Composable
private fun DetailsBody(movieDetails: MovieDetails, modifier: Modifier = Modifier){
    val posterPath = movieDetails.poster_path
    Surface(
        modifier.fillMaxSize()
    ) {
        Box(modifier = modifier.fillMaxSize()){
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://image.tmdb.org/t/p/original$posterPath")
                    .crossfade(true).build(),
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
                    text = movieDetails.title,
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
                        text = movieDetails.release_date,
                        color = Color.LightGray,
                        modifier = rowModifier
                    )
                    Text(
                        text = movieDetails.vote_average.toString(),
                        color = Color.Green,
                        modifier = rowModifier
                    )
                    Text(
                        text = "${movieDetails.runtime} min",
                        color = Color.LightGray
                    )
                }
                Text(
                    text = movieDetails.tagline,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = movieDetails.overview,
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