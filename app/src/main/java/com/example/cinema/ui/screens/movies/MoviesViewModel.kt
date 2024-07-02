
package com.example.cinema.ui.screens.movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.ui.data.TMDBApi
import com.example.cinema.ui.data.model.MediaType
import com.example.cinema.ui.data.model.MoviesResponse
import com.example.cinema.ui.data.model.MoviesResult
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

    var listAllMovies:List<MoviesResult> by mutableStateOf (listOf() )

        /** The mutable State that stores the status of the most recent request */
    var moviesUiState: MoviesUiState by mutableStateOf(MoviesUiState.Loading)
        private set

    init {
        getMovies()
    }

    fun getMovies(page:Int = 1) {
        viewModelScope.launch {
            moviesUiState = MoviesUiState.Loading
            moviesUiState = try {
                val resultMovies = TMDBApi.retrofitService.getPopularMovies(page)
                resultMovies.results.forEach { it.mediaType = MediaType.MOVIE }
                listAllMovies = listAllMovies.plus(resultMovies.results)

                MoviesUiState.Success(resultMovies)
            } catch (e: IOException) {
                MoviesUiState.Error
            } catch (e: HttpException) {
                MoviesUiState.Error
            }
        }
    }
}