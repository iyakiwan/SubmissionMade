package com.project.kotlin.submissionmade.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.project.kotlin.submissionmade.core.data.Resource
import com.project.kotlin.submissionmade.core.ui.MovieAdapter
import com.project.kotlin.submissionmade.databinding.FragmentMovieBinding
import com.project.kotlin.submissionmade.ui.detail.DetailActivity
import com.stone.vega.library.VegaLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { movieId ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, movieId)
                startActivity(intent)
            }

            movieViewModel.listMovies.observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> binding.pbMovie.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.pbMovie.visibility = View.GONE
                            movieAdapter.setData(movies.data)
                        }
                        is Resource.Error -> {
                            binding.pbMovie.visibility = View.GONE
                            Toast.makeText(context, "Oops.. something went wrong", Toast.LENGTH_SHORT).show()
//                            binding.viewError.root.visibility = View.VISIBLE
//                            binding.viewError.tvError.text = movies.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvMovie) {
                layoutManager = VegaLayoutManager()
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