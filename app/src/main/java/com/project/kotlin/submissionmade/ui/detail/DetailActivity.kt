package com.project.kotlin.submissionmade.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.project.kotlin.submissionmade.R
import com.project.kotlin.submissionmade.core.data.source.local.entity.MovieEntity
import com.project.kotlin.submissionmade.core.ui.ViewModelFactory
import com.project.kotlin.submissionmade.databinding.ActivityDetailBinding
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var menu: Menu
    private lateinit var movieEntity: MovieEntity

    private var statusFavorite: Boolean = false


    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val detailMovie = intent.getStringExtra(EXTRA_DATA)
        detailViewModel.getDetailMovie(detailMovie.toString()).observe(this, { detail ->
            if (detail != null) {
                showDetailMovie(detail)
            }
        })
    }

    @SuppressLint("SimpleDateFormat")
    private fun showDetailMovie(detailMovie: MovieEntity?) {
        title = detailMovie?.title
        if (detailMovie != null) {
            movieEntity = detailMovie
        }

        Glide.with(this@DetailActivity)
            .load("https://image.tmdb.org/t/p/w342${detailMovie?.poster}")
            .into(binding.imgDetailPoster)
        Glide.with(this@DetailActivity)
            .load("https://image.tmdb.org/t/p/w500${detailMovie?.backdrop}")
            .into(binding.imgDetailBackdrop)
        binding.tvDetailTitle.text = detailMovie?.title
        binding.tvDetailRating.text = detailMovie?.rating
        var dateRelease: String? = ""
        try {
            val date = SimpleDateFormat("yyyy-MM-dd").parse(detailMovie?.release.toString())
            dateRelease =
                DateFormat.getDateInstance(DateFormat.MEDIUM).format(Objects.requireNonNull(date))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        binding.tvDetailRelease.text = dateRelease

        binding.tvDetailLanguage.text = detailMovie?.language
        binding.tvDetailPopularity.text = detailMovie?.popularity
        binding.tvDetailDesc.text = detailMovie?.overview

        binding.tvDetailAdult.text = if (detailMovie?.adult!!) "Yes" else "No"
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        val menuItem = menu.findItem(R.id.menu_detail_favorite)
        if (statusFavorite) {
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite)
        } else {
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.detail_menu, menu)
        if (menu != null) {
            this.menu = menu
            statusFavorite = movieEntity.isFavorite
            setStatusFavorite(statusFavorite)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_detail_favorite -> {
                statusFavorite = !statusFavorite
                movieEntity.let { detailViewModel.setFavoriteMovie(it, statusFavorite) }
                setStatusFavorite(statusFavorite)
                if (statusFavorite)
                    Toast.makeText(this, "Movie save to favorite", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Movie remove from favorite", Toast.LENGTH_SHORT).show()
                true
            }
            else -> true
        }
    }
}