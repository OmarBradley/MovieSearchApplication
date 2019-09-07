package omarbradley.com.moviesearchapplication.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import omarbradley.com.domain.usecase.SearchMovieUseCase
import omarbradley.com.moviesearchapplication.R
import omarbradley.com.moviesearchapplication.recyclerview.MovieItem
import omarbradley.com.moviesearchapplication.recyclerview.MovieItemDataSourceFactory
import omarbradley.com.moviesearchapplication.recyclerview.movieItemPagedListConfig
import omarbradley.com.util.aac.SingleLiveEvent
import omarbradley.com.util.base.BaseViewModel
import omarbradley.com.util.lazyMap
import omarbradley.com.util.view.invalidate

class MainViewModel(
    private val searchMovieUseCase: SearchMovieUseCase
) : BaseViewModel() {

    private var searchKeyword: String = ""
    private val _toastMessage = MutableLiveData<Int>()
    private val _onClickSearchButtonEvent = SingleLiveEvent<Unit>()
    private val loadSearchedMoviesMap: Map<String, LiveData<PagedList<MovieItem>>> =
        lazyMap { searchKeyword ->
            return@lazyMap LivePagedListBuilder(
                MovieItemDataSourceFactory(searchMovieUseCase, coroutineScope, searchKeyword),
                movieItemPagedListConfig
            ).build()
        }
    val onClickSearchButtonEvent: LiveData<Unit> = _onClickSearchButtonEvent
    val toastMessageRes: LiveData<Int> = _toastMessage
    val loadSearchedMovies: LiveData<PagedList<MovieItem>>
        get() = loadSearchedMoviesMap.getValue(searchKeyword)

    fun invalidateDataSource() {
        loadSearchedMovies.value?.invalidate()
    }

    fun onClickSearchButton() {
        if (searchKeyword.isBlank())
            _toastMessage.value = R.string.message_blank_search_keyword
        else
            _onClickSearchButtonEvent.value = Unit
    }

    fun inputSearchKeyword(searchKeyword: String) {
        this.searchKeyword = searchKeyword
    }

    override fun handleError(error: Throwable) {
        Log.e("handleError", error.toString())
        _toastMessage.value = R.string.message_error
    }

}
