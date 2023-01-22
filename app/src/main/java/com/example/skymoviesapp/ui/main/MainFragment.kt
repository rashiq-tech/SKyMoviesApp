package com.example.skymoviesapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.skymoviesapp.R
import com.example.skymoviesapp.databinding.FragmentMainBinding
import com.example.skymoviesapp.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding : FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding  = FragmentMainBinding.bind(view)

        viewModel.fetchAllMovies()
        viewModel.moviesList.observe(requireActivity()) {
            when (it) {
                is ApiState.Success -> {
//                    binding.progress.visibility = View.GONE
//                    val data = it.data!!.movies
//                    homeAdapter.submitList(data!!)
                }
                is ApiState.Error -> {
//                    binding.progress.visibility = View.GONE
//                    it.message?.let { message ->
//                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
//                    }
                }
                is ApiState.Loading -> {
//                    binding.progress.visibility = View.VISIBLE
                }
            }

        }
    }

}