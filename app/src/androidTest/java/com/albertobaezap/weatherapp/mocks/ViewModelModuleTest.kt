package com.albertobaezap.weatherapp.mocks

import androidx.lifecycle.ViewModelProvider
import com.albertobaezap.weatherapp.di.factory.ViewModelFactory
import com.albertobaezap.weatherapp.di.factory.bindViewModel
import com.albertobaezap.weatherapp.presentation.WeatherViewModel
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Kodein module for testing viewmodel dependencies.
 */
val viewModelModuleTest = Kodein.Module("ViewModelModuleTest") {
    bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(kodein.direct) }
    bindViewModel<WeatherViewModel>() with provider {
        WeatherViewModelMock()
    }
}