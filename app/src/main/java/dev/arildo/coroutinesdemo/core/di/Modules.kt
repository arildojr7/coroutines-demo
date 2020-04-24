package dev.arildo.coroutinesdemo.core.di

import dev.arildo.coroutinesdemo.multiplerequests.viewmodel.MultipleRequestsViewModel
import dev.arildo.coroutinesdemo.singlerequest.viewmodel.SingleRequestViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModules = module {
    viewModel { SingleRequestViewModel(get()) }
    viewModel { MultipleRequestsViewModel(get()) }
}

fun getSingleRequestModules() = listOf(viewModelModules)