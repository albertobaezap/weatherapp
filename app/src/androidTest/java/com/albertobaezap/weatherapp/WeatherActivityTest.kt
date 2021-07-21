package com.albertobaezap.weatherapp

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.albertobaezap.weatherapp.presentation.WeatherActivity
import org.junit.Before
import org.junit.Test

class WeatherActivityTest {
    private lateinit var scenario: ActivityScenario<WeatherActivity>
    private lateinit var activity: WeatherActivity

    @Before
    fun before() {
        scenario = launchActivity()
        scenario.onActivity {
            activity = it
        }
    }

    @Test
    fun getRandomWeather() {
        onView(withId(R.id.weatherButton)).perform(click())
        onView(withText("Dubai")).check(matches(isDisplayed()))
    }
}