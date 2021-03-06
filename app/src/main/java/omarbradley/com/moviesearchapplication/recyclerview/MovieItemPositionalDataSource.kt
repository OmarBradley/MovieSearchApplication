package omarbradley.com.moviesearchapplication.recyclerview

import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import omarbradley.com.domain.entity.Movie
import omarbradley.com.domain.entity.SearchMovieInfo
import omarbradley.com.domain.usecase.DEFAULT_DISPLAY_MOVIE_ITEM_COUNT
import omarbradley.com.domain.usecase.SearchMovieUseCase

class MovieItemDataSourceFactory(
    private val searchMovieUseCase: SearchMovieUseCase,
    private val coroutineScope: CoroutineScope,
    private val searchKeyword: String
) : DataSource.Factory<Int, MovieItem>() {

    override fun create(): DataSource<Int, MovieItem> =
        MovieItemPositionalDataSource(searchMovieUseCase, coroutineScope, searchKeyword)

}

class MovieItemPositionalDataSource(
    private val searchMovieUseCase: SearchMovieUseCase,
    private val coroutineScope: CoroutineScope,
    private val searchKeyword: String
) : PositionalDataSource<MovieItem>() {

    private lateinit var searchMovieInfo: SearchMovieInfo

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MovieItem>) {
        coroutineScope.launch {
            searchMovieInfo = searchMovieUseCase.getSearchMovieInfo(searchKeyword)
            val totalCount = searchMovieInfo.totalCount
            val firstLoadPosition = computeInitialLoadPosition(params, totalCount)
            val firstLoadSize = computeInitialLoadSize(params, firstLoadPosition, totalCount)
            val searchedMovies = searchMovieUseCase.loadMoreSearchedMovies(searchMovieInfo.searchKeyword, firstLoadSize, firstLoadPosition + 1)
                .map(Movie::toMovieItem)
            callback.onResult(searchedMovies, 0)
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieItem>) {
        coroutineScope.launch {
            val totalCount = searchMovieInfo.totalCount
            if (totalCount > params.startPosition) {
                val searchedMovies =
                    searchMovieUseCase.loadMoreSearchedMovies(searchMovieInfo.searchKeyword, params.loadSize, params.startPosition + 1)
                        .map(Movie::toMovieItem)
                callback.onResult(searchedMovies)
            }
        }
    }

}

val movieItemPagedListConfig = PagedList.Config.Builder()
    .setInitialLoadSizeHint(DEFAULT_DISPLAY_MOVIE_ITEM_COUNT)
    .setPageSize(DEFAULT_DISPLAY_MOVIE_ITEM_COUNT)
    .setPrefetchDistance(5)
    .setEnablePlaceholders(false)
    .build()
