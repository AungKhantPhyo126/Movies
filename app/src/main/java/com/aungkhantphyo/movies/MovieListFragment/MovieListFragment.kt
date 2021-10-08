package com.aungkhantphyo.movies.MovieListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.aungkhantphyo.movies.R
import com.aungkhantphyo.movies.databinding.FragmentMoviesListBinding
import com.aungkhantphyo.movies.localdatabase.entity.POPULAR
import com.aungkhantphyo.movies.localdatabase.entity.UPCOMING
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment:Fragment() {
    private lateinit var  binding:FragmentMoviesListBinding
    private val viewModel by viewModels<MoviewListViewModel>()
    private val args by lazy { arguments?.getString("type") }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMoviesListBinding.inflate(inflater,container,false).also {
            binding=it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val adapter = MoviesListRecyclerAdapter({data->
            findNavController().navigate(MoviePagerFragmentDirections.actionMoviePagerFragmentToMovieDetailFragment(
                data.id,data.type
            ))
        },viewModel)
        binding.rvUpcomingMovies.adapter=adapter
        when(args){
            POPULAR->{
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.popularMoviesListLiveData.collectLatest {
                        adapter.submitData(it)
                    }
                }
            }
            UPCOMING->{
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.upcomingMoviesListLiveData.collectLatest {
                        adapter.submitData(it)
                    }
                }
            }
            "favorite"->{
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.favoriteMoviesListLiveData.collectLatest {
                        adapter.submitData(it)
                    }
                }
            }
        }


        adapter.addLoadStateListener { loadState ->

            binding.btnReload.isVisible = loadState.source.refresh is LoadState.Error
            binding.rvUpcomingMovies.isVisible = loadState.source.refresh is LoadState.NotLoading
            if (loadState.source.refresh is LoadState.Loading) {
                binding.lavLoadState.setAnimation(R.raw.cube_loader)
                binding.lavLoadState.visibility = View.VISIBLE
                binding.lavLoadState.playAnimation()
            } else {
                binding.lavLoadState.cancelAnimation()
                binding.lavLoadState.visibility = View.INVISIBLE
            }
        }
        binding.btnReload.setOnClickListener {
            adapter.refresh()
        }
    }
}