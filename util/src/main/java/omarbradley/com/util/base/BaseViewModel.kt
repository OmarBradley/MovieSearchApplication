package omarbradley.com.util.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.plus

abstract class BaseViewModel : ViewModel() {

    protected val coroutineScope: CoroutineScope by lazy { viewModelScope + coroutineExceptionHandler }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, error ->
        handleError(error)
    }

    abstract fun handleError(error: Throwable)

}
