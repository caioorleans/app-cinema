
package com.example.cinema.ui.screens.favorites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.ui.data.TMDBApi
import com.example.cinema.ui.data.model.ActionFavoriteBody
import com.example.cinema.ui.data.model.MediaResponse
import com.example.cinema.ui.data.model.MediaResult
import com.example.cinema.ui.data.model.MediaType
import com.example.cinema.ui.data.model.ActionFavoriteResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface FavoriteUiState {
    data class Success(val result: MediaResponse) : FavoriteUiState
    object Error : FavoriteUiState
    object Loading : FavoriteUiState
}

sealed interface RemoveFavoriteUiState {
    data class Success(val result: ActionFavoriteResponse) : RemoveFavoriteUiState
    object Error : RemoveFavoriteUiState
    object Loading : RemoveFavoriteUiState
}

class FavoriteViewModel : ViewModel() {

    var listAllFavorite:List<MediaResult> by mutableStateOf (listOf() )

    var favoriteRemoveUiState: RemoveFavoriteUiState by mutableStateOf(RemoveFavoriteUiState.Loading)
        private set

    /** The mutable State that stores the status of the most recent request */
    var favoriteUiState: FavoriteUiState by mutableStateOf(FavoriteUiState.Loading)
        private set

    init {
        getFavorite()
    }

    private fun getFavorite() {
        viewModelScope.launch {
            favoriteUiState = FavoriteUiState.Loading
            favoriteUiState = try {
                val resultFavoriteMovies = TMDBApi.retrofitService.getFavoriteMovies()
                resultFavoriteMovies.results.forEach{
                    it.mediaType = MediaType.MOVIE
                }
                val resultFavoriteTvSeries = TMDBApi.retrofitService.getFavoriteTvSeries()
                resultFavoriteTvSeries.results.forEach{
                    it.mediaType = MediaType.SERIE
                }

                listAllFavorite = listAllFavorite.plus(resultFavoriteMovies.results)
                listAllFavorite = listAllFavorite.plus(resultFavoriteTvSeries.results)

                FavoriteUiState.Success(resultFavoriteTvSeries)
            } catch (e: IOException) {
                FavoriteUiState.Error
            } catch (e: HttpException) {
                FavoriteUiState.Error
            }
        }
    }

    fun getNextPageFavorite(nextPage:Int = 2) {
        viewModelScope.launch {
            favoriteUiState = FavoriteUiState.Loading
            favoriteUiState = try {
                val resultFavoriteMovies = TMDBApi.retrofitService.getFavoriteMovies(nextPage)
                val resultFavoriteTvSeries = TMDBApi.retrofitService.getFavoriteTvSeries(nextPage)

                listAllFavorite = listAllFavorite.plus(resultFavoriteMovies.results)
                listAllFavorite = listAllFavorite.plus(resultFavoriteTvSeries.results)
                FavoriteUiState.Success(resultFavoriteTvSeries)
            } catch (e: IOException) {
                FavoriteUiState.Error
            } catch (e: HttpException) {
                FavoriteUiState.Error
            }
        }
    }

    suspend fun removeFavorite(mediaId:Int, mediaType: MediaType){
        favoriteRemoveUiState = RemoveFavoriteUiState.Loading
        viewModelScope.launch {
            favoriteRemoveUiState = try {
                val body = ActionFavoriteBody(
                    false,
                    mediaId,
                    if (mediaType == MediaType.MOVIE) "movie" else "tv"
                )
                val resultRemoveFavorite = TMDBApi.retrofitService.removeFavorite( body )
                RemoveFavoriteUiState.Success(resultRemoveFavorite)
            }catch (e: IOException) {
                RemoveFavoriteUiState.Error
            }
            catch (e: HttpException) {
                RemoveFavoriteUiState.Error
            }
        }
    }



}
