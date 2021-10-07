package com.aungkhantphyo.movies.upcomingMovieListFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aungkhantphyo.movies.domain.UpcomingMovies
import com.aungkhantphyo.movies.localdatabase.entity.UpcomingMoviesEntity
import com.aungkhantphyo.movies.repository.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(
    private val movieRepo:MovieRepo
) :ViewModel(){
    var upcomingMoviesListLiveData=movieRepo.fetchUpcomingMovies().cachedIn(viewModelScope).asLiveData()

    init {

    }
}