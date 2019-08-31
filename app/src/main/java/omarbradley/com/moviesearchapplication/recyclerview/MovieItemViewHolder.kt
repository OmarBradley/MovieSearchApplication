package omarbradley.com.moviesearchapplication.recyclerview

import omarbradley.com.moviesearchapplication.databinding.ItemviewMovieBinding
import omarbradley.com.util.base.BaseDataBindingViewHolder
import omarbradley.com.util.router.startInternetBrowser

class MovieItemViewHolder(
    private val binding: ItemviewMovieBinding
) : BaseDataBindingViewHolder(binding) {

    fun bind(item: MovieItem) {
        with(binding) {
            this.item = item
            executePendingBindings()
        }

        with(itemView) {
            setOnClickListener { context.startInternetBrowser(item.link) }
        }
    }

}
