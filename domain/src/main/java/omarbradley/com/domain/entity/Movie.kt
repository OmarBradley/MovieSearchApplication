package omarbradley.com.domain.entity

data class Movie(
    val thumnailUrl: String,
    val title: String,
    val userRating: Float,
    val openYear: Int,
    val director: List<String>,
    val actors: List<String>,
    val link: String
)
