package com.example.weatherforecast.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.model.Weather
import com.example.weatherforecast.model.WeatherElement
import com.example.weatherforecast.model.WeatherResponse
import com.example.weatherforecast.repository.WeatherRepository
import com.example.weatherforecast.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): BaseViewModel() {

    private val _weatherResponse = MutableLiveData<List<WeatherElement>>()
    val weatherResponse: LiveData<List<WeatherElement>> = _weatherResponse

    fun getWeather(city: String) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val response = weatherRepository.getWeather(city)
            isLoading.postValue(false)
            errorMessage.postValue(Utils.capitalizeFirstCharacter(response.message))
            _weatherResponse.postValue(response.data?.list)
        }
    }

}