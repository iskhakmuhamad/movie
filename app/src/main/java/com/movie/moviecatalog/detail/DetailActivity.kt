package com.movie.moviecatalog.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.movie.moviecatalog.R
import com.movie.moviecatalog.core.domain.model.Movie
import com.movie.moviecatalog.core.ui.MovieAdapter.Companion.BASEIMG_URL
import com.movie.moviecatalog.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA_MOVIE = "extra_data_movie"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA_MOVIE)
        Log.i("DETAIL ACTIVITY", "onCreate: data movie -> " + movie.toString())
        showDetailMovie(movie)
    }

    private fun showDetailMovie(movie: Movie?) {
        movie?.let {
            binding.tvDetailTitle.text = it.title
            binding.tvDetailDescription.text = it.overview
            Glide.with(this@DetailActivity)
                .load(BASEIMG_URL + it.image)
                .into(binding.imgDetail)

            var isFav = movie.fav
            setStatusFavorite(isFav)

            binding.btnAddFav.setOnClickListener {
                isFav = !isFav
                detailViewModel.setFavMovie(movie, isFav)
                setStatusFavorite(isFav)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnAddFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            binding.btnAddFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }
}