package com.aungkhantphyo.movies.upcomingMovieListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.aungkhantphyo.movies.R
import com.aungkhantphyo.movies.databinding.FragmentUpcomingMoviesListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpcomingMoviesListFragment:Fragment() {
    private lateinit var  binding:FragmentUpcomingMoviesListBinding
    private val viewModel by viewModels<UpcomingMoviesViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentUpcomingMoviesListBinding.inflate(inflater,container,false).also {
            binding=it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MoviesListRecyclerAdapter{

        }
        binding.rvUpcomingMovies.adapter=adapter
        viewModel.upcomingMoviesListLiveData.observe(viewLifecycleOwner,{
            adapter.submitData(viewLifecycleOwner.lifecycle,it)
        })
//        adapter.addLoadStateListener { loadState ->
//
//            binding.btnReload.isVisible = loadState.source.refresh is LoadState.Error
//            binding.rvUpcomingMovies.isVisible = loadState.source.refresh is LoadState.NotLoading
//            if (loadState.source.refresh is LoadState.Loading) {
//                binding.lavLoadState.setAnimation(R.raw.cube_loader)
//                binding.lavLoadState.visibility = View.VISIBLE
//                binding.lavLoadState.playAnimation()
//            } else {
//                binding.lavLoadState.cancelAnimation()
//                binding.lavLoadState.visibility = View.INVISIBLE
//            }
//        }
        binding.btnReload.setOnClickListener {
            adapter.refresh()
        }
    }
}