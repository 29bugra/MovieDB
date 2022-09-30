package com.mobillium.movieDB.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobillium.movieDB.R
import com.mobillium.movieDB.core.extensions.pagingListener
import com.mobillium.movieDB.core.extensions.showToast
import com.mobillium.movieDB.core.resources.Resource
import com.mobillium.movieDB.databinding.FragmentHomeBinding
import com.mobillium.movieDB.ui.home.adapter.BannerAdapter
import com.mobillium.movieDB.ui.home.adapter.MovieListAdapter
import com.mobillium.movieDB.ui.home.model.ResultsItem
import com.mobillium.movieDB.utils.observeEvent
import com.mobillium.movieDB.utils.viewBinding
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        private const val MOVIE_ID = "movieId"
    }

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel by inject<HomeViewModel>()

    private val list = ArrayList<ResultsItem>()

    private val bannerListAdapter by lazy(LazyThreadSafetyMode.NONE) {
        BannerAdapter(onRowClick = { _, result ->
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, Bundle().apply {
                putString(MOVIE_ID, result.id.toString())
            })
        })
    }

    private val movieListAdapter by lazy(LazyThreadSafetyMode.NONE) {
        MovieListAdapter(onRowClick = { _, result ->
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, Bundle().apply {
                putString(MOVIE_ID, result.id.toString())
            })
        })
    }

    private val concatRootAdapter: ConcatAdapter by lazy {
        return@lazy ConcatAdapter(bannerListAdapter, movieListAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listener()
        initRecyclerView()
        observeEvent()
    }

    private fun observeEvent() {
        viewModel.movieBannerEvent.observeEvent(viewLifecycleOwner) { resource ->
            binding.progressBar.isVisible = resource == Resource.Loading
            if (resource is Resource.Success) {
                resource.response?.apply {
                    bannerListAdapter.setItems(this.results)
                }
            } else if (resource is Resource.Failure) {
                context?.showToast(resource.message)
            }
        }
        viewModel.movieListEvent.observeEvent(viewLifecycleOwner) { resource ->
            binding.progressBar.isVisible = resource == Resource.Loading
            if (resource is Resource.Success) {
                resource.response?.results?.apply {
                    list.addAll(this)
                    movieListAdapter.setItems(list)
                }
            } else if (resource is Resource.Failure) {
                context?.showToast(resource.message)
            }
        }
        viewModel.pagingEvent.observeEvent(viewLifecycleOwner) { isPaging ->
            if (isPaging) {
                viewModel.getMovieList()
            }
        }
    }

    private fun initRecyclerView() = with(binding.recyclerView) {
        itemAnimator = null
        adapter = concatRootAdapter
        layoutManager = LinearLayoutManager(context)
    }

    private fun listener() = with(binding) {
        recyclerView.pagingListener {
            viewModel.paging()
        }
    }
}