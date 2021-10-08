package com.aungkhantphyo.movies.pagingSource

//class UpcomingMoviesPagingSource(
//    private val movieApiService: MovieApiService,
//    private val appDatabase: AppDatabase
//
//) : PagingSource<Int, MovieEntity>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
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
//    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
//        return  0
////        state.anchorPosition?.let {
////            state.closestPageToPosition(it)?.prevKey?.plus(1)
////                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
////        }
//    }
//
//}