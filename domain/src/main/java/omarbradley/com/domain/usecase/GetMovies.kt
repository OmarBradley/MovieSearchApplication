package omarbradley.com.domain.usecase

private const val DEFAULT_DISPLAY_MOVIE_ITEM_COUNT = 10

/**
 * 1. 초기 로딩 -> 1번째 자리에서 10개를 가져온다 (네이버에선, start : position, display : n개를 가져온다)
 * 2. LoadMore1 -> 11번째 자리에서 10개를 가져옴
 * 3. LoadMore2 -> 21번째 자리에서 10개를 가져옴
 */

class GetMovies {

    fun getMovies(searchKeyword: String, displayItemCount: Int = DEFAULT_DISPLAY_MOVIE_ITEM_COUNT) {

    }

    fun getMoreMovies(searchKeyword: String, displayItemCount: Int = DEFAULT_DISPLAY_MOVIE_ITEM_COUNT, position: Int) {

    }


}