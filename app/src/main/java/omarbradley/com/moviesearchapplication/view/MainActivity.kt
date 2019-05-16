package omarbradley.com.moviesearchapplication.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_main.*
import omarbradley.com.moviesearchapplication.R
import omarbradley.com.moviesearchapplication.databinding.ActivityMainBinding
import omarbradley.com.moviesearchapplication.recyclerview.MovieItemAdapter
import omarbradley.com.util.base.BaseActivity
import omarbradley.com.util.view.addVerticalDecoration
import omarbradley.com.util.view.longToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val adapter: MovieItemAdapter by lazy { MovieItemAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)) {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
        }

        with(recyclerview_searchResult) {
            adapter = this@MainActivity.adapter
            addVerticalDecoration()
        }

        with(viewModel.output) {
            onClickSearchButtonEvent().observe {
                refreshSearchedMovies()
                loadSearchedMovies().observe(adapter::submitList)
            }
            toastMessageRes().observe { longToast(it) }
        }
    }

}