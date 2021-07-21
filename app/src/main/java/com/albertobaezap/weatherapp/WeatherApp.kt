package com.albertobaezap.weatherapp

import android.app.Application
import com.albertobaezap.weatherapp.di.networkModule
import com.albertobaezap.weatherapp.di.viewModelModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import timber.log.Timber

/**
 * Main App module to configure dependencies.
 */
open class WeatherApp: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(networkModule)
        import(viewModelModule)
    }

    override fun onCreate() {
        super.onCreate()

        //Plant timber tree for logging purposes
        Timber.plant(Timber.DebugTree())
    }
}