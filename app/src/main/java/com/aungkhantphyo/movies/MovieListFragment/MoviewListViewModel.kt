package com.aungkhantphyo.movies.MovieListFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.aungkhantphyo.movies.repository.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviewListViewModel @Inject constructor(
    private val movieRepo:MovieRepo
) :ViewModel(){
    var upcomingMoviesListLiveData=movieRepo.fetchUpcomingMovies().cachedIn(viewModelScope)

    var popularMoviesListLiveData=movieRepo.fetchPopularMovies().cachedIn(viewModelScope)

    var favoriteMoviesListLiveData=movieRepo.fetchFavoriteMovies().cachedIn(viewModelScope)

    fun toggleFavorite(movieId:String){
        viewModelScope.launch {
            movieRepo.toggleFavorite(movieId)

        }
    }

}