package com.mobillium.movieDB.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import com.mobillium.movieDB.R
import com.mobillium.movieDB.core.extensions.loadUrl
import com.mobillium.movieDB.core.extensions.showToast
import com.mobillium.movieDB.core.resources.Resource
import com.mobillium.movieDB.databinding.FragmentDetailBinding
import com.mobillium.movieDB.ui.details.model.MovieDetailsResponse
import com.mobillium.movieDB.utils.API_KEY
import com.mobillium.movieDB.utils.IMAGE_PATH
import com.mobillium.movieDB.utils.observeEvent
import com.mobillium.movieDB.utils.viewBinding
import org.koin.android.ext.android.inject

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private val viewModel by inject<DetailViewModel>()

    private var movieId: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            movieId = DetailFragmentArgs.fromBundle(it).movieId
        }
        viewModel.getMovieDetail(movieId = movieId, apiKey = API_KEY)
        observeEvent()
    }

    private fun observeEvent() = with(viewModel) {
        movieDetailEvent.observeEvent(viewLifecycleOwner) { resource ->
            binding.progressBar.isVisible = resource == Resource.Loading
            if (resource is Resource.Success) {
                resource.response?.apply {
                    initView(this)
                }
            } else if (resource is Resource.Failure) {
                context?.showToast(resource.message)
            }
        }
    }

    private fun initView(item: MovieDetailsResponse) = with(binding) {
        binding.imageView.loadUrl(IMAGE_PATH + item.backdrop_path)
        tvDescription.text = item.overview
        tvTitle.text = item.original_title
        tvDate.text = item.release_date
        tvRate.text = item.vote_average.toString()
    }
}