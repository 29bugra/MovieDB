package com.mobillium.movieDB.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobillium.movieDB.core.resources.NetworkResult
import com.mobillium.movieDB.core.resources.Resource
import com.mobillium.movieDB.ui.home.model.MovieBannerListResponse
import com.mobillium.movieDB.ui.home.model.MovieListResponse
import com.mobillium.movieDB.ui.home.repository.HomeRepository
import com.mobillium.movieDB.utils.Event
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository, private val apiKey: String) : ViewModel() {
    private val _movieBannerEvent = MutableLiveData<Event<Resource<MovieBannerListResponse?>>>()
    val movieBannerEvent: LiveData<Event<Resource<MovieBannerListResponse?>>> = _movieBannerEvent

    private val _movieListEvent = MutableLiveData<Event<Resource<MovieListResponse?>>>()
    val movieListEvent: LiveData<Event<Resource<MovieListResponse?>>> = _movieListEvent

    private val _pagingEvent = MutableLiveData<Event<Boolean>>()
    val pagingEvent: LiveData<Event<Boolean>> = _pagingEvent

    private var lastPageIndex: Int = 1
    private var totalPage: Int? = 0

    private fun pagingControl() = lastPageIndex > totalPage ?: 0

    fun paging() {
        _pagingEvent.value = Event(pagingControl(), true)
    }

    init {
        getMovieBannerList()
        getMovieList()
    }

    private fun getMovieBannerList() {
        viewModelScope.launch {
            _movieBannerEvent.value = Event(Resource.Loading)
            when (val result = repository.getMovieBannerList(apiKey)) {
                is NetworkResult.Failure -> {
                    _movieBannerEvent.value = Event(Resource.Failure(result.error))
                }
                is NetworkResult.Success -> {
                    val marketBasketResponse = result.response
                    _movieBannerEvent.value = Event(Resource.Success(marketBasketResponse))
                }
            }
        }
    }

    fun getMovieList() {
        viewModelScope.launch {
            _movieListEvent.value = Event(Resource.Loading)
            when (val result = repository.getMovieList(apiKey, lastPageIndex.toString())) {
                is NetworkResult.Failure -> {
                    _movieListEvent.value = Event(Resource.Failure(result.error))
                }
                is NetworkResult.Success -> {
                    val marketBasketResponse = result.response
                    _movieListEvent.value = Event(Resource.Success(marketBasketResponse))
                    totalPage = result.response?.totalPages
                    lastPageIndex++
                }
            }
        }
    }
}