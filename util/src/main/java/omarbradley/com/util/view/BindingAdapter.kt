package omarbradley.com.util.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestManager

@BindingAdapter("imageUrl", "requestManager")
fun loadImage(imageView: ImageView, imageUrl: String, requestManager: RequestManager) {
    requestManager
        .load(imageUrl)
        .into(imageView)
}
