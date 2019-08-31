package omarbradley.com.moviesearchapplication.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.bumptech.glide.RequestManager
import omarbradley.com.moviesearchapplication.databinding.ItemviewMovieBinding
import omarbradley.com.util.withNullable

class MovieItemAdapter(
    private val requestManager: RequestManager
) : PagedListAdapter<MovieItem, MovieItemViewHolder>(MovieItem.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder =
        MovieItemViewHolder(
            ItemviewMovieBinding.inflate(LayoutInflater.from(parent.context)),
            requestManager
        )

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        withNullable(getItem(position)) {
            holder.bind(this)
        }
    }

}
