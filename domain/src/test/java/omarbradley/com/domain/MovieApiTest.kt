package omarbradley.com.domain

import io.kotlintest.shouldNotBe
import io.kotlintest.specs.BehaviorSpec
import omarbradley.com.domain.repository.remote.api.MovieApi
import omarbradley.com.domain.repository.remote.api.movieApi
import omarbradley.com.domain.repository.remote.json.request.SearchMovieQuery
import omarbradley.com.domain.repository.remote.json.request.toQueryMap

class MovieApiTest : BehaviorSpec({

    val api: MovieApi by lazy {
        movieApi
    }

    given("'엑스맨' 검색어가 주어진다") {
        val queryMap = SearchMovieQuery("액스맨").toQueryMap()

        `when`("영화 검색 api 호출 시") {
            val response = api.getMovies(queryMap)
                .await()

            then("'액스맨' 영화 정보를 가져온다") {
                println("data ${response.items}")
                response shouldNotBe null
            }
        }
    }
})