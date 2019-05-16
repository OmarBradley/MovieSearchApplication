package omarbradley.com.moviesearchapplication.recyclerview

import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import omarbradley.com.domain.entity.Movie
import omarbradley.com.domain.usecase.DEFAULT_DISPLAY_MOVIE_ITEM_COUNT
import omarbradley.com.domain.usecase.SearchMovieUseCase
import omarbradley.com.util.withIoContext

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

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieItem>) {
        coroutineScope.launch {
            val totalCount = withIoContext {
                totalCount(searchKeyword)
            }
            if (totalCount > params.startPosition) {
                val searchedMovies =
                    searchMovieUseCase.loadMoreSearchedMovies(searchKeyword, params.loadSize, params.startPosition + 1)
                        .map(Movie::toMovieItem)
                callback.onResult(searchedMovies)
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MovieItem>) {
        coroutineScope.launch {
            val totalCount = withIoContext {
                totalCount(searchKeyword)
            }
            val firstLoadPosition = computeInitialLoadPosition(params, totalCount)
            val firstLoadSize = computeInitialLoadSize(params, firstLoadPosition, totalCount)
            val searchedMovies =
                searchMovieUseCase.loadMoreSearchedMovies(searchKeyword, firstLoadSize, firstLoadPosition + 1)
                    .map(Movie::toMovieItem)
            callback.onResult(searchedMovies, 0)
        }
    }

    private suspend fun totalCount(searchKeyword: String) =
        searchMovieUseCase.searchMoviesTotalCount(searchKeyword)

}

val movieItemPagedListConfig = PagedList.Config.Builder()
    .setInitialLoadSizeHint(DEFAULT_DISPLAY_MOVIE_ITEM_COUNT)
    .setPageSize(DEFAULT_DISPLAY_MOVIE_ITEM_COUNT)
    .setPrefetchDistance(5)
    .setEnablePlaceholders(false)
    .build()
