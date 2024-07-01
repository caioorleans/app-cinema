package com.example.cinema.ui.screens.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.ui.data.TMDBApi
import com.example.cinema.ui.data.model.AddFavoriteBody
import com.example.cinema.ui.data.model.MediaType
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AddFavoriteUiState{
    object Sucess:AddFavoriteUiState
    object Error:AddFavoriteUiState
}

class AddFavoriteViewModel: ViewModel() {
    var uiState:AddFavoriteUiState by mutableStateOf(AddFavoriteUiState.Sucess)
        private set

    suspend fun addToFavorite(mediaId:Int, mediaType: MediaType){
        viewModelScope.launch {
            uiState = try {
                val body = AddFavoriteBody(
                    true,
                    mediaId,
                    if (mediaType == MediaType.MOVIE) "movie" else "serie"
                )
                TMDBApi.retrofitService.addFavorite(
                    body
                )
                AddFavoriteUiState.Sucess
            }catch (e: IOException) {
                AddFavoriteUiState.Error
            }
            catch (e: HttpException) {
                AddFavoriteUiState.Error
            }
        }
    }
}