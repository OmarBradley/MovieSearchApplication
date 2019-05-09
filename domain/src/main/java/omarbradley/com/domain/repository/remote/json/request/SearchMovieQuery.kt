package omarbradley.com.domain.repository.remote.json.request

data class SearchMovieQuery(
    val query: String,
    val display: Int? = null,
    val start: Int? = null
)

fun SearchMovieQuery.toQueryMap() =
    hashMapOf("query" to query)
        .apply {
            display?.let { put("display", it.toString()) }
            start?.let { put("start", it.toString()) }
        }
