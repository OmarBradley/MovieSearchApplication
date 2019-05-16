package omarbradley.com.moviesearchapplication.recyclerview

import omarbradley.com.domain.entity.Movie

data class MovieItem(
    val thumnailUrl: String,
    val titleText: String,
    val directorsText: String,
    val userRating: Float,
    val openYearText: String,
    val actorsText: String,
    val link: String
)

fun Movie.toMovieItem() =
    MovieItem(
        thumnailUrl = thumnailUrl,
        titleText = title,
        directorsText = director.joinToString(),
        userRating = userRating / 2.0F,
        openYearText = openYear.toString(),
        actorsText = actors.joinToString(),
        link = link
    )
