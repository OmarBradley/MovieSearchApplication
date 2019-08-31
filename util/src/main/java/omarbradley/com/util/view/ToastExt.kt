package omarbradley.com.util.view

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.shortToast(@StringRes messageRes: Int) =
    Toast.makeText(this, getString(messageRes), Toast.LENGTH_SHORT).show()
