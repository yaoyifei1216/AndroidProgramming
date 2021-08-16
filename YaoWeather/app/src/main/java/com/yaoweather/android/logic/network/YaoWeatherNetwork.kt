package com.yaoweather.android.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object YaoWeatherNetwork {
    //获取数据的服务
    private val mPlaceService = ServiceCreator.create(PlaceService::class.java)
    private val mWeatherService = ServiceCreator.create(WeatherService::class.java)
    //获取数据的具体实现方法
    suspend fun searchPlaces(query: String) = mPlaceService.searchPlaces(query).await()

    suspend fun getRealtimeWeather(lng: String, lat: String) =
        mWeatherService.getRealtimeWeather(lng, lat).await()

    suspend fun getDailyWeather(lng: String, lat: String) =
        mWeatherService.getDailyWeather(lng, lat).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}