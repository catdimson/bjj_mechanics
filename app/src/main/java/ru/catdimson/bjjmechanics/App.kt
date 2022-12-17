package ru.catdimson.bjjmechanics

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.catdimson.bjjmechanics.di.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                generals,

                actionsScreen,

                sectionsScreen,
                sectionDetailsScreen,

                termsScreen,
                termDetailsScreen,

                authScreen
            )
        }
    }
}