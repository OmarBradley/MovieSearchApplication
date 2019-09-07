package omarbradley.com.domain.usecase

import omarbradley.com.domain.repository.base.MovieRepository
import omarbradley.com.domain.repository.remote.json.request.SearchMovieQuery

const val DEFAULT_DISPLAY_MOVIE_ITEM_COUNT = 10

class SearchMovieUseCase(
    private val movieRepository: MovieRepository
) {

    suspend fun getSearchMovieInfo(searchKeyword: String) =
        movieRepository.getSearchMovieInfo(SearchMovieQuery(searchKeyword))

    suspend fun loadMoreSearchedMovies(searchKeyword: String, displayItemCount: Int = DEFAULT_DISPLAY_MOVIE_ITEM_COUNT, position: Int) =
        movieRepository.getMovies(SearchMovieQuery(searchKeyword, displayItemCount, position))

}
