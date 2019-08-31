package omarbradley.com.moviesearchapplication.recyclerview

import com.bumptech.glide.RequestManager
import omarbradley.com.moviesearchapplication.databinding.ItemviewMovieBinding
import omarbradley.com.util.base.BaseDataBindingViewHolder
import omarbradley.com.util.router.startInternetBrowser

class MovieItemViewHolder(
    private val binding: ItemviewMovieBinding,
    private val requestManager: RequestManager
) : BaseDataBindingViewHolder(binding) {

    fun bind(item: MovieItem) {
        with(binding) {
            this.item = item
            requestManager = this@MovieItemViewHolder.requestManager
            executePendingBindings()
        }

        with(itemView) {
            setOnClickListener { context.startInternetBrowser(item.link) }
        }
    }

}
