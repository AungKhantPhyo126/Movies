package com.aungkhantphyo.movies.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aungkhantphyo.movies.di.API_KEY
import com.aungkhantphyo.movies.localdatabase.AppDatabase
import com.aungkhantphyo.movies.localdatabase.entity.UpcomingMoviesEntity
import com.aungkhantphyo.movies.network.MovieApiService
import com.aungkhantphyo.movies.network.dto.asEntity
import retrofit2.HttpException
import java.io.IOException

//class UpcomingMoviesPagingSource(
//    private val movieApiService: MovieApiService,
//    private val appDatabase: AppDatabase
//
//) : PagingSource<Int, UpcomingMoviesEntity>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpcomingMoviesEntity> {
//
//        return try {
//            val currentPageNumber = params.key?:1
//            val upcomingMoviesList=response.body()?.results?.map { it.asEntity() }
//
//            LoadResult.Page(
//                data=upcomingMoviesList.orEmpty(),
//                prevKey = null,
//                nextKey = if (currentPageNumber == response.body()?.totalPages) null else currentPageNumber + 1
//            )
//
//        } catch (e: IOException) {
//            return LoadResult.Error(e)
//        } catch (e: HttpException) {
//            return LoadResult.Error(e)
//        } catch (t:Throwable){
//            return LoadResult.Error(t)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, UpcomingMoviesEntity>): Int? {
//        return  0
////        state.anchorPosition?.let {
////            state.closestPageToPosition(it)?.prevKey?.plus(1)
////                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
////        }
//    }
//
//}