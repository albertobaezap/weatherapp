# WeatherApp
Display the weather of a random location using the Open Weather Map API

![Screenshot_1626856606](https://user-images.githubusercontent.com/6639333/126458405-cbb4853d-e49c-4f95-97c2-1646057abcbd.png)

## Solution overview
Created using Clean architecture, with MVVM pattern, Kodein DI and Retrofit coroutines.

## Layers description

![clean2 (1)](https://user-images.githubusercontent.com/6639333/126457052-ac2e3e4c-bf67-4fcc-a335-0f9f81f938b9.png)

### Data layer
The data layer contains all the network resources and repositories. Everything is implemented with suspend functions.
* WeatherApi: Retrofit interface for endpoint calls. Only the basic getWeatherData function was implemented. This will return a remote model which will only serialize the basic parameters of the model.
* WeatherDataRemoteDataSource: Usually this naming means that also a local data source could exist, but I only implemented the remote. This class is used to map the serialized remote model to a local Entity in order to separate remote and app's contexts and handle network errors.
* WeatherRepositoryImpl: Implementation of the Repository interface that will call the required dataSources / mappers to compose the response.

### Domain layer
This layer includes the entities and use cases of the application.
* WeatherData: Main model for the app's functionality. Only contains the minimum required fields and all data is already processed to use in the presentation layer. (i.e. the icon is already converted to URL by the data source).
* WeatherDataRepository: Interface that is used to talk with the Data layer.
* GetWeather: Main use case that will call the data layer to retrieve a proper WeatherData object given a set of random coordinates.

### Presentation layer
This layer is in charge of displaying the information on the screen and interact with the user using a MVVM pattern with Architecture Components.
* WeatherActivity: Main activity of the application that interacts with the WeatherViewModel. It only contains a button and subscribes to the weather liveData via the Observer pattern to paint the latest weather on the screen.
* WeatherViewModel: This ViewModel uses the WeatherData model to return all the data to the Activity. Also generates a random set of coordinates to call the GetWeather usecase.

## Dependency Injection
Kodein was used as the DI manager because of it's easy configuration and usage. 
Two modules were needed for the app's main functionality:
* Network module: Consists on all the classes of the Network layer and the Retrofit singleton instance.
* ViewModel module: Android has its own viewModel injection methods in Architecture Components, but I needed to override it in order to use my own Kodein ViewModel instance with the aid of the ViewModelFactory and extensions. This is a common practice when working with Kodein. Same as the Testing environment that I will explain in the next point.

## Testing
I created a basic set of tests to cover the Activity, Repository and UseCase using mockk for simple dependency mocking in unit tests and espresso for instrumentation tests.
To work with my own ViewModel mock instance, I had to override the Kodein module injection in the App, so I had to create a few environment classes and a TestRunner in order to do this.

## Libraries
* Architecture components
* Kodein
* Timber
* Retrofit
* Coroutines
* Picasso
* Mockk
