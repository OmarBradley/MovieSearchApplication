package omarbradley.com.util.router

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.startInternetBrowser(uri: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
}
