package com.yaoweather.android.logic.model

import com.google.gson.annotations.SerializedName

/**
 * @Description: 描述实时天气信息
 * @author: yaoyifei
 * @CreateDate: 2021/8/16 22:47
 */

data class RealtimeResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)
    data class Realtime(
        val skycon: String,
        val temperature: Float,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)

}