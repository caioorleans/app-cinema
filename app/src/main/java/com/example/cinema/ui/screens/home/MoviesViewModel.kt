
package com.example.cinema.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.ui.network.MoviesApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface MoviesUiState {
    data class Success(val photos: String) : MoviesUiState
    object Error : MoviesUiState
    object Loading : MoviesUiState
}

class MoviesViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var moviesUiState: MoviesUiState by mutableStateOf(MoviesUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMovies()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MoviesPhoto] [List] [MutableList].
     */
    fun getMovies() {
        viewModelScope.launch {
            moviesUiState = MoviesUiState.Loading
            moviesUiState = try {
                val listResult = MoviesApi.retrofitService.getMovies()
                MoviesUiState.Success(
                    "Success: ${listResult.size} Movies retrieved"
                )
            } catch (e: IOException) {
                MoviesUiState.Error
            } catch (e: HttpException) {
                MoviesUiState.Error
            }
        }
    }
}