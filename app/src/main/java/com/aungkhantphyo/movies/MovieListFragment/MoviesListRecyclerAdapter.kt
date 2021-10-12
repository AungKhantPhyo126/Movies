package com.aungkhantphyo.movies.MovieListFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aungkhantphyo.movies.R
import com.aungkhantphyo.movies.databinding.ItemRecyclerUpcomingMovieBinding
import com.aungkhantphyo.movies.domain.Movie

class MoviesListRecyclerAdapter(
    private val navigateToDetail: (data: Movie) -> Unit,
    private val viewModel: MoviewListViewModel
) :
    PagingDataAdapter<Movie, UpcomingMoviesViewHolder>(MoviesDiffCallback) {
    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerUpcomingMovieBinding.inflate(layoutInflater, parent, false)
        return UpcomingMoviesViewHolder(binding, navigateToDetail, viewModel)
    }

}

object MoviesDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}

class UpcomingMoviesViewHolder(
    private val binding: ItemRecyclerUpcomingMovieBinding,
    private val navigateToDetail: (data: Movie) -> Unit,
    private val viewModel: MoviewListViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.ivFav.setOnClickListener {
            viewModel.toggleFavorite(movie.id)
        }
        if (movie.isFav) {
            binding.ivFav.setImageResource(R.drawable.ic_baseline_star_24)
        } else binding.ivFav.setImageResource(R.drawable.ic_baseline_star_white_24)

        binding.movies = movie
        binding.root.setOnClickListener {
            navigateToDetail(movie)
        }
    }
}