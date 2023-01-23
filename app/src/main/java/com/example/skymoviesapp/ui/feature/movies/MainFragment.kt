package com.example.skymoviesapp.ui.feature.movies

import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.skymoviesapp.R
import com.example.skymoviesapp.data.models.Movies
import com.example.skymoviesapp.databinding.FragmentMainBinding
import com.example.skymoviesapp.ui.feature.movies.adapter.MoviesListAdapter
import com.example.skymoviesapp.ui.feature.movies.viewmodel.MainViewModel
import com.example.skymoviesapp.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), MenuProvider {

    private var spanCount: Int = 3
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding
    private val newsFeedListAdapter = MoviesListAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)
        activity?.addMenuProvider(this, viewLifecycleOwner)

        initRv()
        observeData()
    }

    private fun observeData() {
        viewModel.fetchAllMovies()
        viewModel.moviesList.observe(requireActivity()) {
            when (it) {
                is ApiState.Success -> {
                    binding.tvError.visibility = View.GONE
                    binding.rvMovies.visibility = View.VISIBLE
                    val data = it.data!!
                    newsFeedListAdapter.submitList(data)
                }
                is ApiState.Error -> {
                    binding.tvError.visibility = View.VISIBLE
                    binding.rvMovies.visibility = View.GONE
                    it.message?.let { message ->
                        binding.tvError.text = message
                    }
                }
                is ApiState.Loading -> {
                    binding.tvError.text = getString(R.string.api_state_loading)
                    binding.tvError.visibility = View.VISIBLE
                    binding.rvMovies.visibility = View.GONE
                }
            }

        }
    }

    private fun initRv() {
        binding.rvMovies.apply {
            adapter = newsFeedListAdapter
            layoutManager = GridLayoutManager(requireContext(), spanCount)
            setHasFixedSize(true)
            addItemDecoration(RecyclerItemDecoration(spanCount, 10))
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchText: String): Boolean {
                filterRvData(searchText)
                return false
            }
        })
    }

    private fun filterRvData(searchText: String) {

        val filteredList: ArrayList<Movies> = ArrayList()

        for (item in viewModel.moviesList.value!!.data!!) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.genre.lowercase(Locale.ROOT).contains(searchText.lowercase(Locale.ROOT)) ||
                item.title.lowercase(Locale.ROOT).contains(searchText.lowercase(Locale.ROOT))
            ) {
                // if the item is matched we are adding it to our filtered list.
                filteredList.add(item)
            }
        }
        if (filteredList.isNotEmpty()) {
            newsFeedListAdapter.submitList(filteredList)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        spanCount =
            if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 3
            else 5
        initRv()
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return true
    }

    class RecyclerItemDecoration(private val spanCount: Int, private val spacing: Int) :
        RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {

            val spacing = Math.round(spacing * parent.context.resources.displayMetrics.density)
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount

            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount

            outRect.top = if (position < spanCount) spacing else 0
            outRect.bottom = spacing
        }

    }


}