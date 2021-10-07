package com.aungkhantphyo.movies.upcomingMovieListFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aungkhantphyo.movies.databinding.ItemRecyclerUpcomingMovieBinding
import com.aungkhantphyo.movies.domain.UpcomingMovies

class MoviesListRecyclerAdapter(val navigateToDetail: () -> Unit) :
    PagingDataAdapter<UpcomingMovies,UpcomingMoviesViewHolder>(MoviesDiffCallback) {
    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerUpcomingMovieBinding.inflate(layoutInflater, parent, false)
        return UpcomingMoviesViewHolder(binding, navigateToDetail)
    }

}

object MoviesDiffCallback: DiffUtil.ItemCallback<UpcomingMovies>() {

    override fun areItemsTheSame(oldItem: UpcomingMovies, newItem: UpcomingMovies): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UpcomingMovies, newItem: UpcomingMovies): Boolean {
        return oldItem == newItem
    }
}

class UpcomingMoviesViewHolder(
    private val binding: ItemRecyclerUpcomingMovieBinding,
    private val navigateToDetail: () -> Unit
):RecyclerView.ViewHolder(binding.root){
    init {
        binding.root.setOnClickListener {
            navigateToDetail()
        }
    }
    fun bind(upComingMoviesDomain: UpcomingMovies) {
        binding.upcomingmovies=upComingMoviesDomain
    }
}