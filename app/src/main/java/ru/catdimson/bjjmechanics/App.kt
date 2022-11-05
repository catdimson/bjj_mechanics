package ru.catdimson.bjjmechanics

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.catdimson.bjjmechanics.di.generals
import ru.catdimson.bjjmechanics.di.sectionDetailsScreen
import ru.catdimson.bjjmechanics.di.sectionsScreen

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                generals,

                sectionsScreen,
                sectionDetailsScreen
            )
        }
    }
}