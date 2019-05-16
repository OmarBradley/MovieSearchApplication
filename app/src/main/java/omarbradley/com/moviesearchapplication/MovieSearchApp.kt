package omarbradley.com.moviesearchapplication

import android.app.Application
import android.content.Context
import omarbradley.com.moviesearchapplication.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieSearchApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieSearchApp)
            modules(appModule)
        }
    }

}
