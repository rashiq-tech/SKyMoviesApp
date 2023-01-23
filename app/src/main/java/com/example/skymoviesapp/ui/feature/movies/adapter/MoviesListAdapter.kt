package com.example.skymoviesapp.ui.feature.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skymoviesapp.R
import com.example.skymoviesapp.data.models.Movies
import com.example.skymoviesapp.databinding.RowItemMoviesBinding


class MoviesListAdapter ():
    ListAdapter<Movies, MoviesListAdapter.NewsFeedViewHolder>(MoviesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        val binding = RowItemMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsFeedViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NewsFeedViewHolder,
        position: Int
    ) {
        val currentItem = getItem(position)
        if(currentItem != null){
            holder.bind(currentItem)
        }


    }
    inner class NewsFeedViewHolder(
        private val binding: RowItemMoviesBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition

                if(position!=RecyclerView.NO_POSITION){
                    val item = getItem(position)

                }
            }
        }
        fun bind(article: Movies){
            binding.apply {
                Glide.with(itemView)
                    .load(article.poster)
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(ivMovies)
                tvGenre.text = article.genre ?: ""
            }
        }

    }

    class MoviesDiffUtil : DiffUtil.ItemCallback<Movies>() {

        override fun areItemsTheSame(oldItem: Movies, newItem: Movies) =
            oldItem.imdbID == newItem.imdbID

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies) =
            oldItem == newItem
    }
}

