package com.aungkhantphyo.movies.movieDetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.aungkhantphyo.movies.ViewState
import com.aungkhantphyo.movies.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment:Fragment() {
    private lateinit var binding:FragmentMovieDetailBinding
    private val viewModel by viewModels<MovieDetailViewModel>()
    private val args by navArgs<MovieDetailFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMovieDetailBinding.inflate(inflater,container,false).also {
            binding=it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieDetail(args.movieId)

        binding.favAnimationView.setMinAndMaxFrame(10, 60)
        binding.favAnimationView.setOnClickListener {
            viewModel.toggleFavorite(args.movieId)
        }
        var hasplayed=false
        viewModel.isFavLiveData.observe(viewLifecycleOwner,{
            if (it){
                binding.favAnimationView.speed = 1f
                binding.favAnimationView.playAnimation()
                hasplayed=true
            }else{
                if (hasplayed){
                    binding.favAnimationView.speed = -1f
                    binding.favAnimationView.resumeAnimation()
                    hasplayed = false
                }
            }
        })





        viewModel.movieDetail.observe(viewLifecycleOwner, {
            binding.movie=it
            binding.executePendingBindings()
        })
    }
}