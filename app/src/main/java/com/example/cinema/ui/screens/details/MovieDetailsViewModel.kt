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
import com.example.cinema.ui.data.model.VideoInfo
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MovieDetailsUiState {
    data class Success(val result: MediaResult) : MovieDetailsUiState
    data object Error : MovieDetailsUiState
    data object Loading : MovieDetailsUiState
}

sealed interface TrailerIdState {
    data class IsNotNull(val id:String): TrailerIdState
    data object IsNull:TrailerIdState
}

class MovieDetailsViewModel(
    private val movieId:Int,
    val mediaType: MediaType
): ViewModel() {
    var movieUiState:MovieDetailsUiState by mutableStateOf(MovieDetailsUiState.Loading)
        private set

    var trailerState:TrailerIdState by mutableStateOf(TrailerIdState.IsNull)
        private set

    var showTrailer by mutableStateOf(false)

    init {
        getMovieDetails(movieId)
        getMovieTrailerId(movieId)
    }

    private fun getMovieDetails(movieId:Int){
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

    private fun getMovieTrailerId(mediaId: Int){
        viewModelScope.launch {
            trailerState = try {
                val videos =
                    if(mediaType == MediaType.MOVIE) TMDBApi.retrofitService.getMovieVideos(mediaId)
                    else TMDBApi.retrofitService.getTvVideos(mediaId)

                var trailers = videos.results.filter { it.type == "Trailer" }
                if (trailers.isEmpty()){
                    trailers = videos.results.filter { it.type == "Teaser" }
                }
                TrailerIdState.IsNotNull(trailers[0].key)
            }
            catch (e: IOException) {
                e.message?.let { Log.e("", it) }
                TrailerIdState.IsNull
            }
            catch (e: HttpException) {
                e.message?.let { Log.e("", it) }
                TrailerIdState.IsNull
            }
            catch (e: IndexOutOfBoundsException){
                e.message?.let { Log.e("", it) }
                TrailerIdState.IsNull
            }
        }
    }
}