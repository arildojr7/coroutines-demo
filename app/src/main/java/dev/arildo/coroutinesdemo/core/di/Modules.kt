package dev.arildo.coroutinesdemo.core.di

import dev.arildo.coroutinesdemo.singlerequest.viewmodel.SingleRequestViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val singleRequestModules = module {
    viewModel { SingleRequestViewModel(get()) }
}

fun getSingleRequestModules() = listOf(singleRequestModules)