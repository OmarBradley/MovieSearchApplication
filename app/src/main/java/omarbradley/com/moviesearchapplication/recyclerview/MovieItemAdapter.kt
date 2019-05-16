package omarbradley.com.moviesearchapplication.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import omarbradley.com.moviesearchapplication.databinding.ItemviewMovieBinding
import omarbradley.com.util.withNullable

class MovieItemAdapter : PagedListAdapter<MovieItem, MovieItemViewHolder>(DiffMovieItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder =
        MovieItemViewHolder(ItemviewMovieBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        withNullable(getItem(position)) {
            holder.bind(this)
        }
    }

}

class DiffMovieItemCallBack : DiffUtil.ItemCallback<MovieItem>() {

    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
        oldItem.link == newItem.link

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
        oldItem == newItem

}
