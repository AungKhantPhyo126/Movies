package com.aungkhantphyo.movies.MovieListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aungkhantphyo.movies.R
import com.aungkhantphyo.movies.databinding.FragmentMoviePagerBinding
import com.aungkhantphyo.movies.localdatabase.entity.POPULAR
import com.aungkhantphyo.movies.localdatabase.entity.UPCOMING
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoviePagerFragment: Fragment() {
    private lateinit var binding:FragmentMoviePagerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMoviePagerBinding.inflate(inflater,container,false).also{
            binding=it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pager.adapter=MoviePagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text=getString(R.string.upcoming)
                }
                1 -> {
                    tab.text=getString(R.string.popular)
                }
                2->{
                    tab.text="Favorite"
                }

            }
        }.attach()
    }
}

class MoviePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val movieType = when(position){
            0->  UPCOMING
            1-> POPULAR
            else -> "favorite"
        }
        val fragment = MovieListFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putString("type",movieType)
        }
        return fragment
    }
}

