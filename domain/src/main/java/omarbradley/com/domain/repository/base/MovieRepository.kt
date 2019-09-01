package omarbradley.com.domain.repository.base

import omarbradley.com.domain.entity.Movie
import omarbradley.com.domain.entity.TotalCount
import omarbradley.com.domain.repository.remote.json.request.SearchMovieQuery

interface MovieRepository {

    suspend fun getTotalItemCount(query: SearchMovieQuery): TotalCount

    suspend fun getMovies(query: SearchMovieQuery): List<Movie>

}
