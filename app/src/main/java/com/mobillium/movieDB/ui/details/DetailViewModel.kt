package com.mobillium.movieDB.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobillium.movieDB.ui.details.repository.DetailRepository
import com.mobillium.movieDB.core.resources.NetworkResult
import com.mobillium.movieDB.core.resources.Resource
import com.mobillium.movieDB.ui.details.model.MovieDetailsResponse
import com.mobillium.movieDB.utils.Event
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: DetailRepository) : ViewModel() {
    private val _movieDetailEvent = MutableLiveData<Event<Resource<MovieDetailsResponse?>>>()
    val movieDetailEvent: LiveData<Event<Resource<MovieDetailsResponse?>>> = _movieDetailEvent

    fun getMovieDetail(movieId: String, apiKey: String) {
        viewModelScope.launch {
            _movieDetailEvent.value = Event(Resource.Loading)
            when (val result = repository.getMovieDetail(movieId, apiKey)) {
                is NetworkResult.Failure -> {
                    _movieDetailEvent.value = Event(Resource.Failure(result.error), true)
                }
                is NetworkResult.Success -> {
                    val marketBasketResponse = result.response
                    _movieDetailEvent.value = Event(Resource.Success(marketBasketResponse))
                }
            }
        }
    }
}