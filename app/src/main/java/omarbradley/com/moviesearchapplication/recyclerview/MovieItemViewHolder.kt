package omarbradley.com.moviesearchapplication.recyclerview

import omarbradley.com.moviesearchapplication.databinding.ItemviewMovieBinding
import omarbradley.com.util.base.BaseDataBindingViewHolder

class MovieItemViewHolder(
    private val binding: ItemviewMovieBinding
) : BaseDataBindingViewHolder(binding) {

    fun bind(item: MovieItem) {
        with(binding) {
            this.item = item
            executePendingBindings()
        }
        itemView.setOnClickListener {
            // 웹뷰로 이동!
        }
    }

}
