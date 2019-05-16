package omarbradley.com.moviesearchapplication.module

import omarbradley.com.domain.repository.base.MovieRepository
import omarbradley.com.domain.repository.remote.api.RemoteMovieRepository
import omarbradley.com.domain.repository.remote.api.movieApi
import omarbradley.com.domain.usecase.SearchMovieUseCase
import omarbradley.com.moviesearchapplication.view.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // app
    viewModel { MainViewModel(get()) }

    // domain
    single { SearchMovieUseCase(get()) }

    // repository
    single { RemoteMovieRepository(movieApi) as MovieRepository }

}
