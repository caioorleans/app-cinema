package com.example.cinema.ui.screens.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.cinema.ui.data.TMDBApi
import com.example.cinema.ui.data.model.MediaResult
import com.example.cinema.ui.data.model.MediaType
import com.example.cinema.ui.data.model.MovieDetails
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MovieDetailsUiState {
    data class Success(val result: MediaResult) : MovieDetailsUiState
    data object Error : MovieDetailsUiState
    data object Loading : MovieDetailsUiState
}

class MovieDetailsViewModel(
    private val movieId:Int,
    val mediaType: MediaType
): ViewModel() {
    var movieUiState:MovieDetailsUiState by mutableStateOf(MovieDetailsUiState.Loading)
        private set

    init {
        getMovieDetails(movieId)
    }

    fun getMovieDetails(movieId:Int){
        viewModelScope.launch {
            movieUiState = MovieDetailsUiState.Loading
            movieUiState = try {
                val result =
                    if(mediaType == MediaType.MOVIE) TMDBApi.retrofitService.getMovieDetails(movieId)
                    else TMDBApi.retrofitService.getTvShowDetails(movieId)
                result.mediaType = mediaType
                MovieDetailsUiState.Success(result)
            }catch (e: IOException) {
                e.message?.let { Log.e("", it) }
                MovieDetailsUiState.Error
            }
            catch (e: HttpException) {
                e.message?.let { Log.e("", it) }
                MovieDetailsUiState.Error
            }
        }
    }
}