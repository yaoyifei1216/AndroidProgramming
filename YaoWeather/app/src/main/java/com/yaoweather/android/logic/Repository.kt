package com.yaoweather.android.logic

import androidx.lifecycle.liveData
import com.yaoweather.android.logic.dao.PlaceDao
import com.yaoweather.android.logic.model.Place
import com.yaoweather.android.logic.model.Weather
import com.yaoweather.android.logic.network.YaoWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * @Description: 仓库类，用来返回数据给调用方
 * @author: yaoyifei
 * @CreateDate: 2021/8/12 23:30
 */
object Repository {
    /**
     * @description 返回给上一层一个liveData对象
     * @param
     * @return
     */
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = YaoWeatherNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("placeResponse status is ${placeResponse.status}"))
        }
    }

    /**
     * @description 获取天气数据
     * @param
     * @return
     */
    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtime = async { YaoWeatherNetwork.getRealtimeWeather(lng, lat) }
            val deferredDaily = async { YaoWeatherNetwork.getDailyWeather(lng, lat) }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather =
                    Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(RuntimeException("realtimeResponse status is ${realtimeResponse.status}" + "dailyResponse status is ${dailyResponse.status}"))
            }
        }
    }

    fun savePlace(place: Place) = PlaceDao.savePlace(place);

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

    /**
     * @description 网络请求统一入口，避免每次都要try catch
     * @param
     * @return
     */
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

}