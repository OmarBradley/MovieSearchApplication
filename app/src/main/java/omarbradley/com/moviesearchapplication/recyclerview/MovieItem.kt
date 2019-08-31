package omarbradley.com.moviesearchapplication.recyclerview

import androidx.recyclerview.widget.DiffUtil
import omarbradley.com.domain.entity.Movie

data class MovieItem(
    val thumnailUrl: String,
    val titleText: String,
    val directorsText: String,
    val userRating: Float,
    val openYearText: String,
    val actorsText: String,
    val link: String
) {

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem areItemsTheSame newItem

            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem areContentsTheSame newItem
        }
    }

}

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

infix fun MovieItem.areItemsTheSame(another: MovieItem): Boolean =
    (link == another.link)

infix fun MovieItem.areContentsTheSame(another: MovieItem): Boolean =
    (thumnailUrl == another.thumnailUrl)
            && (titleText == another.titleText)
            && (directorsText == another.directorsText)
            && (userRating == another.userRating)
            && (openYearText == another.openYearText)
            && (actorsText == another.actorsText)
