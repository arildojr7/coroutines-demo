package dev.arildo.data.di

import dev.arildo.data.RetrofitInitializer
import dev.arildo.data.songs.SongsDataSource
import dev.arildo.data.songs.SongsRepository
import dev.arildo.data.songs.SongsRepositoryImpl
import dev.arildo.data.songs.datasource.local.SongsLocalDataSource
import dev.arildo.data.songs.datasource.local.database.SongsDatabase
import dev.arildo.data.songs.datasource.remote.SongsRemoteDataSource
import org.koin.dsl.module

private val dbModule = module {
    single { SongsDatabase(get()) }
    single { get<SongsDatabase>().songDao() }
}

private val apiServiceModule = module {
    single { RetrofitInitializer().songsApiService() }
}

private val repositoryModule = module {
    single<SongsRepository> { SongsRepositoryImpl(get(),get()) }
}

private val dataSourceModule = module {
    single<SongsDataSource.Local> { SongsLocalDataSource(get()) }
    single<SongsDataSource.Remote> { SongsRemoteDataSource(get()) }
}

fun getDataModules() = listOf(apiServiceModule, repositoryModule, dataSourceModule, dbModule)