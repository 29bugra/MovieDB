package com.mobillium.movieDB.ui.details.di

import com.mobillium.movieDB.ui.details.repository.DetailRepository
import com.mobillium.movieDB.ui.details.repository.DetailRepositoryImpl
import com.mobillium.movieDB.core.resources.HandleDataSource
import com.mobillium.movieDB.ui.details.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    factory { HandleDataSource(retrofit = get()) }
    factory<DetailRepository> { DetailRepositoryImpl(service = get(), handleDataSource = get()) }
    viewModel { DetailViewModel(repository = get()) }
}
