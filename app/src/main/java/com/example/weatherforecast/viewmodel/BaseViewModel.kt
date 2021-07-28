package com.example.weatherforecast.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>().apply { value = false }

}