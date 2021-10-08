package com.aungkhantphyo.movies.MovieListFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aungkhantphyo.movies.databinding.ItemRecyclerUpcomingMovieBinding
import com.aungkhantphyo.movies.domain.Movie

class MoviesListRecyclerAdapter(private val navigateToDetail: (data:Movie) -> Unit,
                                private val viewModel:MoviewListViewModel) :
    PagingDataAdapter<Movie,UpcomingMoviesViewHolder>(MoviesDiffCallback) {
    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerUpcomingMovieBinding.inflate(layoutInflater, parent, false)
        return UpcomingMoviesViewHolder(binding, navigateToDetail,viewModel)
    }

}

object MoviesDiffCallback: DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}

class UpcomingMoviesViewHolder(
    private val binding: ItemRecyclerUpcomingMovieBinding,
    private val navigateToDetail: (data:Movie) -> Unit,
    private val viewModel:MoviewListViewModel
):RecyclerView.ViewHolder(binding.root){

    fun bind(movie: Movie) {
        binding.favAnimationView.setMinAndMaxFrame(10, 60)
        binding.favAnimationView.setOnClickListener {
            viewModel.toggleFavorite(movie.id)
        }
        var hasplayed=false
        if (movie.isFav){
            binding.favAnimationView.speed = 1f
            binding.favAnimationView.playAnimation()
            hasplayed=true
        }else{
            if (hasplayed){
                binding.favAnimationView.speed = -1f
                binding.favAnimationView.resumeAnimation()
                hasplayed = true
            }
        }
        binding.movies=movie
        binding.root.setOnClickListener {
            navigateToDetail(movie)
        }
    }
}