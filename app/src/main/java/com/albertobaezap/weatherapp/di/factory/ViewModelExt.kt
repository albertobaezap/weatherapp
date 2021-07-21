package com.albertobaezap.weatherapp.di.factory

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance

/**
 * Extension that allows to bind a viewModel to the Kodein instance.
 */
inline fun <reified T : ViewModel> Kodein.Builder.bindViewModel(overrides: Boolean? = null): Kodein.Builder.TypeBinder<T> {
    return bind<T>(T::class.java.simpleName, overrides)
}

/**
 * Extension that allows the injection of a viewModel in the Activity.
 */
inline fun <reified VM : ViewModel, T> T.kodeinViewModel(): Lazy<VM> where T : KodeinAware, T : AppCompatActivity {
    return lazy { ViewModelProvider(this, direct.instance()).get(VM::class.java) }
}