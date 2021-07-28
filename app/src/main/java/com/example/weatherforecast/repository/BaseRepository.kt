package com.example.weatherforecast.repository

import android.content.Context
import com.example.weatherforecast.R
import com.example.weatherforecast.api.Resource
import com.example.weatherforecast.model.ErrorResponse
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

open class BaseRepository {

    @Inject
    @ApplicationContext
    lateinit var context: Context

    suspend fun <T> callApi(call: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(it)
                } ?: Resource.Error(null, context.getString(R.string.something_went_wrong))
            } else {
                response.errorBody()?.let {
                    val errorResponse = Gson().fromJson(it.charStream(), ErrorResponse::class.java)
                    Resource.Error(null, errorResponse.message)
                } ?: Resource.Error(null, response.message())
            }
        } catch (e: IOException) {
            Resource.Error(null, context.getString(R.string.check_internet))
        } catch (e: Exception) {
            Resource.Error(null, e.message)
        }
    }
}