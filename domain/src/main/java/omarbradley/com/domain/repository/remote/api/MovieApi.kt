package omarbradley.com.domain.repository.remote.api

import kotlinx.coroutines.Deferred
import omarbradley.com.domain.repository.remote.json.response.MovieJson
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieApi {

    @GET("search/movie.json")
    fun getMovies(@QueryMap queries: Map<String, String>): Deferred<MovieJson>


}
