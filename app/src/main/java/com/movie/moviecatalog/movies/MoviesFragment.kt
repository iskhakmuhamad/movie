package com.movie.moviecatalog.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.movie.moviecatalog.core.data.Resource
import com.movie.moviecatalog.core.ui.MovieAdapter
import com.movie.moviecatalog.databinding.FragmentMoviesBinding
import com.movie.moviecatalog.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private val moviesViewModel: MoviesViewModel by viewModel()
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { movie ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA_MOVIE, movie)
                startActivity(intent)
            }

            moviesViewModel.movies.observe(viewLifecycleOwner, Observer{
                when (it) {
                    is Resource.Loading -> binding.progresMbar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progresMbar.visibility = View.GONE
                        movieAdapter.setData(it.data)
                    }
                    is Resource.Error -> {
                        binding.progresMbar.visibility = View.GONE
                    }
                }
            })

            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}