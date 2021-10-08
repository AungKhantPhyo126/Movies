package com.aungkhantphyo.movies.movieDetailFragment

import androidx.lifecycle.*
import com.aungkhantphyo.movies.domain.Movie
import com.aungkhantphyo.movies.repository.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.aungkhantphyo.movies.ViewState
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel(){
    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetail:LiveData<Movie>
    get() = _movieDetail
    private val _isFav=MutableLiveData<Boolean>()
    val isFavLiveData:LiveData<Boolean>
    get() = _isFav
    fun getMovieDetail(movieId:String){
        viewModelScope.launch {
            val result=movieRepo.getMovieDetail(movieId)
            _movieDetail.value=result
            _isFav.value=result.isFav
        }
    }
    fun toggleFavorite(movieId:String){
        viewModelScope.launch {
            movieRepo.toggleFavorite(movieId)
            _isFav.value=_isFav.value?.not()
        }
    }
}