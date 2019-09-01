package omarbradley.com.domain.entity

data class TotalCount(
    val count: Int
)

fun Int.toTotalCount() = TotalCount(this)
