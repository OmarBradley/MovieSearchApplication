package omarbradley.com.domain.repository.remote.api

import omarbradley.com.domain.repository.remote.json.response.MovieJson
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieApi {

    @GET("search/movie.json")
    suspend fun getMovies(@QueryMap queries: Map<String, String>): MovieJson

}
