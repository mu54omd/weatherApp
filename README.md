# A Simple and sample Weather App

A Simple/sample weather app that use [open-meteo](https://open-meteo.com) based on Jetpack Compose!
This app will allow users to pick a city and see what the weather situation is right now, hourly, and for the next several days. ⁤The architecture of our app is based on Google's recommended app architecture, which comprises three distinct layers - Domain, Data, and UI.


![app](https://github.com/user-attachments/assets/98078bdf-9fc8-4b4c-a8d0-abc08966e861)


## Open-Meteo Weather API
We use the Open-Meteo API; it is an open-source weather API with a free tariff for non-commercial use. It is flexible, and it easily integrates with what we currently have, which suits the needs of our application. Note that, apart from the mentioned API, the Open-Meteo website provides other APIs such as Historical Weather, Flood, Climate Change, and many others to suit the specific requirements necessary for their use. Such additional APIs will be used when needed for a specific project in further expanding the spectrum of what can be done using the Open-Meteo platform.
## The Libraries
* [**Room**](https://developer.android.com/jetpack/androidx/releases/room): Provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* [**DataStore**](https://developer.android.com/topic/libraries/architecture/datastore): A data storage solution that allows you to store key-value pairs or typed objects with protocol buffers.
* [**Material3**](https://m3.material.io): The latest version of Google’s open-source design system.
* [**Retrofit**](https://square.github.io/retrofit/): A type-safe HTTP client for Android and Java.
* [**Arrow**](https://arrow-kt.io/learn/overview/): Comprises different libraries, each improving or extending one commonly-used library in the Kotlin ecosystem or a particular Kotlin language feature.
* [**Hilt**](https://developer.android.com/training/dependency-injection/hilt-android): 
* [**PersianDate**](https://github.com/samanzamani/PersianDate?tab=readme-ov-file#example): Persian date for Android.
* [**Vico**](https://patrykandpatrick.com/vico/wiki/): A light and extensible chart library for Android.

## Medium articles about this app
[Part 1: App Architecture and API Setup]()

[Part 2: Local database, Local preferences and States]()

[Part 3: ViewModels, Screens, and the rest of the story]()
  

