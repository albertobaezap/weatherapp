package com.albertobaezap.weatherapp.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.albertobaezap.weatherapp.R
import com.albertobaezap.weatherapp.di.factory.kodeinViewModel
import com.squareup.picasso.Picasso
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein

class WeatherActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val viewModel: WeatherViewModel by kodeinViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val city = findViewById<TextView>(R.id.cityName)
        val icon = findViewById<ImageView>(R.id.weatherIcon)
        val text = findViewById<TextView>(R.id.weatherText)
        val coords = findViewById<TextView>(R.id.cityCoords)

        findViewById<Button>(R.id.weatherButton).setOnClickListener {
            viewModel.getRandomWeather()
        }

        viewModel.weatherData.observe(this, {
            city.text = it.city
            text.text = it.weather
            coords.text = "${it.coordinates.latitude}, ${it.coordinates.longitude}"
            Picasso.with(this)
                .load(it.icon)
                .into(icon)
        })
    }
}