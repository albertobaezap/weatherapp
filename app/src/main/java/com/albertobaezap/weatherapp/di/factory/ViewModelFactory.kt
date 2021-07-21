package com.albertobaezap.weatherapp.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.DKodein
import org.kodein.di.generic.instanceOrNull

/**
 * Factory class that will provide a ViewModel class with Kodein dependencies.
 */
class ViewModelFactory(private val injector: DKodein) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return injector.instanceOrNull<ViewModel>(tag = modelClass.simpleName) as T?
            ?: modelClass.newInstance()
    }
}
