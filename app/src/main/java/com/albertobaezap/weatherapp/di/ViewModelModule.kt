package com.albertobaezap.weatherapp.di

import androidx.lifecycle.ViewModelProvider
import com.albertobaezap.weatherapp.di.factory.ViewModelFactory
import com.albertobaezap.weatherapp.di.factory.bindViewModel
import com.albertobaezap.weatherapp.presentation.WeatherViewModel
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Kodein module for viewmodel dependencies.
 */
val viewModelModule = Kodein.Module("ViewModelModule") {
    bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(kodein.direct) }
    bindViewModel<WeatherViewModel>() with provider {
        WeatherViewModel(instance())
    }
}