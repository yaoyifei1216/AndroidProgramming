package com.yaoweather.android.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yaoweather.android.logic.Repository
import com.yaoweather.android.logic.model.Location

/**
 * @Description: 天气的ViewModel
 * @author: yaoyifei
 * @CreateDate: 2021/8/17 0:02
 */
class WeatherViewModel : ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()
    var locationLng = ""
    var locationLat = ""
    var placeName = ""
    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(
            location.lng,
            location.lat
        )
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }
}