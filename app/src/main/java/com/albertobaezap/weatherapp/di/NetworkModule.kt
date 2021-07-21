package com.albertobaezap.weatherapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.albertobaezap.weatherapp.BuildConfig
import com.albertobaezap.weatherapp.data.network.WeatherApi
import com.albertobaezap.weatherapp.data.repository.WeatherDataRepositoryImpl
import com.albertobaezap.weatherapp.data.sources.WeatherDataRemoteDataSource
import com.albertobaezap.weatherapp.di.factory.ViewModelFactory
import com.albertobaezap.weatherapp.di.factory.bindViewModel
import com.albertobaezap.weatherapp.domain.repository.WeatherDataRepository
import com.albertobaezap.weatherapp.domain.usecases.GetWeather
import com.albertobaezap.weatherapp.presentation.WeatherViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Kodein module for all the network dependencies.
 */
val networkModule = Kodein.Module("NetworkModule") {

    bind<OkHttpClient>() with singleton {
        OkHttpClient.Builder()
            .addInterceptor(getAuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }

    bind<Retrofit>() with singleton { getRetrofitInstance(instance()) }
    bind<WeatherApi>() with singleton { getWeatherApi(instance()) }

    bind<WeatherDataRemoteDataSource>() with singleton { getWeatherRemoteDataSource(instance()) }
    bind<WeatherDataRepositoryImpl>() with singleton { getWeatherRepository(instance()) }
    bind<GetWeather>() with singleton { getWeather(instance()) }
}

private fun getAuthInterceptor() =
    Interceptor {
        val request = it.request()

        val url = request.url.newBuilder()
            .addQueryParameter("appid", BuildConfig.APP_ID)
            .build()

        val newRequest = request.newBuilder().url(url).build()
        it.proceed(newRequest)
    }

private fun getRetrofitInstance(client: OkHttpClient) =
    Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

private fun getWeatherApi(retrofit: Retrofit) =
    retrofit.create(WeatherApi::class.java)

private fun getWeatherRemoteDataSource(api: WeatherApi) =
    WeatherDataRemoteDataSource(api)

private fun getWeatherRepository(remoteDataSource: WeatherDataRemoteDataSource) =
    WeatherDataRepositoryImpl(remoteDataSource)

private fun getWeather(repository: WeatherDataRepository) =
    GetWeather(repository)