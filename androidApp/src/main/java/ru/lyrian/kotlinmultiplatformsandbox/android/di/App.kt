package ru.lyrian.kotlinmultiplatformsandbox.android.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import ru.lyrian.kotlinmultiplatformsandbox.core.di.initializeKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        initializeKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(androidModules())
        }
    }
}
