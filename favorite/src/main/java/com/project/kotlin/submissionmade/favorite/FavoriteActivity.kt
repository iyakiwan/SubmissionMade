package com.project.kotlin.submissionmade.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.project.kotlin.submissionmade.core.ui.MovieAdapter
import com.project.kotlin.submissionmade.favorite.databinding.ActivityFavoriteBinding
import com.project.kotlin.submissionmade.ui.detail.DetailActivity
import com.stone.vega.library.VegaLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Favorite Movie"

        loadKoinModules(favoriteModule)

        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = { movieId ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, movieId)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovie.observe(this, { dataMovie ->
            movieAdapter.setData(dataMovie)
            binding.viewEmpty.viewEmpty.visibility = if (dataMovie.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvFavorite) {
            layoutManager = VegaLayoutManager()
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}