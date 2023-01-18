package ru.lyrian.kotlinmultiplatformsandbox.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

// Android call
fun initializeKoin(koinApplication: KoinApplication.() -> Unit) {
    startKoin {
        koinApplication()
        modules(commonModules())
    }
}

// IOS call
fun initializeKoin() = initializeKoin {  }
