
package com.example.cinema.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.ui.data.TMDBApi
import com.example.cinema.ui.data.model.MediaResponse
import com.example.cinema.ui.data.model.MediaResult
import com.example.cinema.ui.data.model.MediaType
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface MediaUiState {
    data class Success(val result: MediaResponse) : MediaUiState
    object Error : MediaUiState
    object Loading : MediaUiState
}

class MediaViewModel : ViewModel() {

    var listAllMedia:List<MediaResult> by mutableStateOf (listOf() )

        /** The mutable State that stores the status of the most recent request */
    var mediaUiState: MediaUiState by mutableStateOf(MediaUiState.Loading)
        private set

    init {
        getMedia()
    }

    private fun getMedia() {
        viewModelScope.launch {
            mediaUiState = MediaUiState.Loading
            mediaUiState = try {
                val resultTvSeries = TMDBApi.retrofitService.getPopularTvSeries()
                resultTvSeries.results.forEach{
                    it.mediaType = MediaType.SERIE
                }
                val resultMovies = TMDBApi.retrofitService.getPopularMovies()
                resultMovies.results.forEach {
                    it.mediaType = MediaType.MOVIE
                }

                listAllMedia = listAllMedia.plus(resultMovies.results)
                listAllMedia = listAllMedia.plus(resultTvSeries.results)

                MediaUiState.Success(resultTvSeries)
            } catch (e: IOException) {
                MediaUiState.Error
            } catch (e: HttpException) {
                MediaUiState.Error
            }
        }
    }

    fun getNextPageMedia(nextPage:Int = 2) {
        viewModelScope.launch {
            mediaUiState = MediaUiState.Loading
            mediaUiState = try {
                val resultMovies = TMDBApi.retrofitService.getPopularMovies(nextPage)
                resultMovies.results.forEach {
                    it.mediaType = MediaType.MOVIE
                }
                val resultTvSeries = TMDBApi.retrofitService.getPopularTvSeries(nextPage)
                resultTvSeries.results.forEach{
                    it.mediaType = MediaType.SERIE
                }

                listAllMedia = listAllMedia.plus(resultMovies.results)
                listAllMedia = listAllMedia.plus(resultTvSeries.results)
                MediaUiState.Success(resultTvSeries)
            } catch (e: IOException) {
                MediaUiState.Error
            } catch (e: HttpException) {
                MediaUiState.Error
            }
        }
    }

}