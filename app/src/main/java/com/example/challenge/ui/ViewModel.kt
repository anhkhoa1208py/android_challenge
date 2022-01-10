package com.example.challenge.ui
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge.api.WeatherService
import com.example.challenge.models.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class ViewModel : ViewModel() {
    lateinit var weatherService: WeatherService

    init {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .client(getOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            weatherService = retrofit.create(WeatherService::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
        val OkHttpClient = builder.build()
        return OkHttpClient
    }

    fun getWeatherDetail(city: String, returnWeather: (WeatherModel?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val weatherAPI = weatherService.getWeatherDetail(city = city, 7, "60c6fbeb4b93ac653c492ba806fc346d")
                withContext(Dispatchers.Main) {
                    returnWeather(weatherAPI)
                }
            }catch (e: Exception){
                //TODO
            }

        }
    }

}
