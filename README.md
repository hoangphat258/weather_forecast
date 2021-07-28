# weather_forecast
a) The project is developed with MVVM pattern. Also, It applies Dependency Injection. Besides, the app uses R8 to make the code not decompiled.

b) 
- Project structure folder:
  + api: contains all API interfaces and one Resource.kt file as error handling for each response.
  + di: There are classes for dependency injection: AppModule.kt and MyApplication.kt.
  + model: All models returned by the server.
  + repository: There is a BaseRepository.kt for making all request to the server. This class was made for any furthur development. WeatherRepository.kt to make request for getting weather forecast.
  + viewmodel: There is a BaseViewModel.kt for getting loading status and error message. This class was made for any furthur development. WeatherViewModel.kt to get response from the server and set value for LiveData.
  + ui: There is a BaseActivity.kt for making some functions that every other Activities can reuse. WeatherActivity.kt is the activity that shows weather forecast.
  + utils: contains some utilities classes. Especially, there is a DeviceUtils.kt class to check whether the devices is rooted.
 - Libraries and frameworks: Retrofit (making API call), Hilt (Dependency Injection), Coroutines, LiveData.

 c) Step to make the project run on local computer:
  - Clone the project and open it with Android Studio.
  - Waiting for gradle syncing.
  - Run project.
 
 d) Checklist of items has been done
 - Programming language: Kotlin.
 - Design app's architecture: MVVM.
 - Apply LiveData mechanism.
 - UI should be looks like in attachment.
 - Secure Android app from:
   + Decompiled APK.
   + Rooted device.
 - Exception handling.
