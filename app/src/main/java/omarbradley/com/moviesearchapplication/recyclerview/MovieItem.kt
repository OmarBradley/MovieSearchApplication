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
        thumnailUrl = this.thumnailUrl,
        titleText = this.title,
        directorsText = this.director.joinToString(),
        userRating = this.userRating,
        openYearText = this.openYear.toString(),
        actorsText = this.actors.joinToString(),
        link = this.link
    )
