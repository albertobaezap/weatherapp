package com.albertobaezap.weatherapp.mocks

import com.albertobaezap.weatherapp.WeatherApp
import com.albertobaezap.weatherapp.di.networkModule
import org.kodein.di.Kodein

/**
 * Main App module to configure dependencies.
 */
class WeatherAppTest: WeatherApp() {
    override val kodein = Kodein.lazy {
        import(networkModule)
        import(viewModelModuleTest)
    }
}