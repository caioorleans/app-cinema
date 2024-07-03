
package com.example.cinema.ui.screens.TvSeries

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinema.ui.data.TMDBApi
import com.example.cinema.ui.data.model.TvSeriesResponse
import com.example.cinema.ui.data.model.TvSeriesResult
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface TvSeriesUiState {
    data class Success(val result: TvSeriesResponse) : TvSeriesUiState
    object Error : TvSeriesUiState
    object Loading : TvSeriesUiState
}

class TvSeriesViewModel : ViewModel() {
    val stateScroll = LazyGridState()

    var listAllTvSeries:List<TvSeriesResult> by mutableStateOf (listOf() )

        /** The mutable State that stores the status of the most recent request */
    var tvSeriesUiState: TvSeriesUiState by mutableStateOf(TvSeriesUiState.Loading)
        private set

    init {
        getTvSeries()
    }

    fun getTvSeries(page:Int = 1) {
        viewModelScope.launch {
            tvSeriesUiState = TvSeriesUiState.Loading
            tvSeriesUiState = try {
                val resultTvSeries = TMDBApi.retrofitService.getPopularTvSeries(page)
                listAllTvSeries = listAllTvSeries.plus(resultTvSeries.results)

                TvSeriesUiState.Success(resultTvSeries)
            } catch (e: IOException) {
                TvSeriesUiState.Error
            } catch (e: HttpException) {
                TvSeriesUiState.Error
            }
        }
    }
}