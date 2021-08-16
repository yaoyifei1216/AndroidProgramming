package com.yaoweather.android.logic.network

import com.yaoweather.android.YaoWeatherApplication
import com.yaoweather.android.logic.model.DailyResponse
import com.yaoweather.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Description: 获取天气数据的网络服务
 * @author: yaoyifei
 * @CreateDate: 2021/8/16 23:13
 */
interface WeatherService {
    @GET("v2.5/${YaoWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<RealtimeResponse>

    @GET("v2.5/${YaoWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}