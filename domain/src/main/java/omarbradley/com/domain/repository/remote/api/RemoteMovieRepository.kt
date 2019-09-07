package omarbradley.com.domain.repository.remote.api

import omarbradley.com.domain.entity.Movie
import omarbradley.com.domain.entity.SearchMovieInfo
import omarbradley.com.domain.repository.base.MovieRepository
import omarbradley.com.domain.repository.remote.json.request.SearchMovieQuery
import omarbradley.com.domain.repository.remote.json.request.toQueryMap
import omarbradley.com.domain.repository.remote.json.response.MovieJson
import omarbradley.com.domain.repository.remote.json.response.toMovie

class RemoteMovieRepository(
    private val api: MovieApi
) : MovieRepository {

    override suspend fun getSearchMovieInfo(query: SearchMovieQuery): SearchMovieInfo =
        api.getMovies(query.toQueryMap())
            .run { SearchMovieInfo(total, query.query) }

    override suspend fun getMovies(query: SearchMovieQuery): List<Movie> =
        api.getMovies(query.toQueryMap())
            .items
            .map(MovieJson.ItemJson::toMovie)

}
