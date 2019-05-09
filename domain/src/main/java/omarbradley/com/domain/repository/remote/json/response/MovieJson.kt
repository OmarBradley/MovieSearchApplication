package omarbradley.com.domain.repository.remote.json.response

import omarbradley.com.domain.entity.Movie
import omarbradley.com.util.splitBar

data class MovieJson(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<ItemJson>
) {

    data class ItemJson(
        val title: String?,
        val link: String?,
        val image: String?,
        val subtitle: String?,
        val pubDate: Int?,
        val director: String?,
        val actor: String?,
        val userRating: Float?
    )

}

fun MovieJson.ItemJson.toMovie() =
    Movie(
        thumnailUrl = image ?: throw NullPointerException(),
        title = title ?: throw NullPointerException(),
        userRating = userRating ?: throw NullPointerException(),
        openYear = pubDate ?: throw NullPointerException(),
        director = director?.splitBar() ?: throw NullPointerException(),
        actors = actor?.splitBar() ?: throw NullPointerException(),
        link = link ?: throw NullPointerException()
    )
