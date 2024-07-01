package com.example.cinema.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.cinema.ui.data.TMDBApi
import com.example.cinema.ui.data.model.MovieDetails
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MovieDetailsUiState {
    data class Success(val result: MovieDetails) : MovieDetailsUiState
    object Error : MovieDetailsUiState
    object Loading : MovieDetailsUiState
}

class DetailsViewModel(
    val movieId:Int
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
                val result = TMDBApi.retrofitService.getMovieDetails(movieId)
                MovieDetailsUiState.Success(result)
            }catch (e: IOException) {
                MovieDetailsUiState.Error
            }
            catch (e: HttpException) {
                MovieDetailsUiState.Error
            }
        }
    }
}