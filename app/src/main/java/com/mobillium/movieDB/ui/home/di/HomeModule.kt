package com.mobillium.movieDB.ui.home.di

import com.mobillium.movieDB.ui.home.HomeViewModel
import com.mobillium.movieDB.core.resources.HandleDataSource
import com.mobillium.movieDB.ui.home.repository.HomeRepository
import com.mobillium.movieDB.ui.home.repository.HomeRepositoryImpl
import com.mobillium.movieDB.utils.API_KEY
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory { HandleDataSource(retrofit = get()) }
    factory<HomeRepository> { HomeRepositoryImpl(service = get(), handleDataSource = get()) }
    viewModel { HomeViewModel(repository = get(), apiKey = API_KEY) }
}
