
package com.example.cinema.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cinema.ui.data.TMDBApi
import com.example.cinema.ui.data.model.MoviesResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface MoviesUiState {
    data class Success(val result: MoviesResponse) : MoviesUiState
    object Error : MoviesUiState
    object Loading : MoviesUiState
}

class MoviesViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var moviesUiState: MoviesUiState by mutableStateOf(MoviesUiState.Loading)
        private set

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            moviesUiState = MoviesUiState.Loading
            moviesUiState = try {
                val result = TMDBApi.retrofitService.getPopularMovies()
                MoviesUiState.Success(result)
            } catch (e: IOException) {
                MoviesUiState.Error
            } catch (e: HttpException) {
                MoviesUiState.Error
            }
        }
    }
}