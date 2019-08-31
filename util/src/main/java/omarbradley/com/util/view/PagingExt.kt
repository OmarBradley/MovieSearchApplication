package omarbradley.com.util.view

import androidx.paging.PagedList

fun <T> PagedList<T>.invalidate() = dataSource.invalidate()
