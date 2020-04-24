package dev.arildo.coroutinesdemo

import android.app.Application
import dev.arildo.coroutinesdemo.core.di.getSingleRequestModules
import dev.arildo.data.di.getDataModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(getDataModules() + getSingleRequestModules() )
        }
    }
}