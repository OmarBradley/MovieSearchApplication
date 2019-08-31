package omarbradley.com.util.aac

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

// SingleLiveEvent 를 자세히, 왜 만들었는지 알고 싶다면
// https://www.notion.so/omarbradley/LiveData-Trouble-shooting-01eca506dcaa42a09fccbfe080adebaa 를 참고하기 바랍니다!
open class SingleLiveEvent<T> : MutableLiveData<T>() {

    private var pending = false

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe(owner, Observer { t ->
            if (pending) {
                pending = false
                Log.w(TAG, "pending ${pending}")
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        Log.w(TAG, "setValue ${pending}")
        pending = true
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }

    companion object {
        private const val TAG = "SingleLiveEvent"
    }
}
