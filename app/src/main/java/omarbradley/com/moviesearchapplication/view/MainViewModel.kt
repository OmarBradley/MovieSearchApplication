package omarbradley.com.moviesearchapplication.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import omarbradley.com.domain.usecase.SearchMovieUseCase
import omarbradley.com.moviesearchapplication.R
import omarbradley.com.moviesearchapplication.recyclerview.MovieItem
import omarbradley.com.moviesearchapplication.recyclerview.MovieItemDataSourceFactory
import omarbradley.com.moviesearchapplication.recyclerview.movieItemPagedListConfig
import omarbradley.com.util.base.BaseViewModel
import omarbradley.com.util.view.refreshDataSource

class MainViewModel(
    private val searchMovieUseCase: SearchMovieUseCase
) : BaseViewModel() {

    val input by lazy {
        object : Input {
            override fun onClickSearchButton() {
                if (searchKeyword.value.isNullOrBlank())
                    toastMessage.value = R.string.message_blank_search_keyword
                else
                    onClickSearchButtonEvent.value = Unit
            }

            override fun inputSearchKeyword(searchKeyword: String) {
                this@MainViewModel.searchKeyword.value = searchKeyword
            }
        }
    }

    private val searchKeyword = MutableLiveData<String>()
    private val toastMessage = MutableLiveData<Int>()
    private val onClickSearchButtonEvent = MutableLiveData<Unit>()

    val output by lazy {
        object : Output {
            override fun onClickSearchButtonEvent() = onClickSearchButtonEvent
            override fun refreshSearchedMovies(): Unit? = loadSearchedMovies().value?.refreshDataSource()
            override fun loadSearchedMovies(): LiveData<PagedList<MovieItem>> = LivePagedListBuilder(
                MovieItemDataSourceFactory(searchMovieUseCase, coroutineScope, searchKeyword.value ?: ""),
                movieItemPagedListConfig
            ).build()

            override fun searchKeyword(): LiveData<String> = searchKeyword
            override fun toastMessageRes(): LiveData<Int> = toastMessage
        }
    }

    override fun handleError(error: Throwable) {
        toastMessage.value = R.string.message_error
    }

    interface Input {
        fun onClickSearchButton()
        fun inputSearchKeyword(searchKeyword: String)
    }

    interface Output {
        fun onClickSearchButtonEvent(): LiveData<Unit>
        fun refreshSearchedMovies(): Unit?
        fun loadSearchedMovies(): LiveData<PagedList<MovieItem>>
        fun searchKeyword(): LiveData<String>
        fun toastMessageRes(): LiveData<Int>
    }

}
