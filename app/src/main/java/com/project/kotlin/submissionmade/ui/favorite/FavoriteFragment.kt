package com.project.kotlin.submissionmade.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.kotlin.submissionmade.core.ui.MovieAdapter
import com.project.kotlin.submissionmade.core.ui.ViewModelFactory
import com.project.kotlin.submissionmade.databinding.FragmentFavoriteBinding
import com.project.kotlin.submissionmade.ui.detail.DetailActivity
import com.stone.vega.library.VegaLayoutManager

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
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

            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner, { dataMovie ->
                movieAdapter.setData(dataMovie)
                if (dataMovie.isEmpty()) Toast.makeText(context, "Data is Empty", Toast.LENGTH_SHORT).show()
            })

            with(binding.rvFavorite) {
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