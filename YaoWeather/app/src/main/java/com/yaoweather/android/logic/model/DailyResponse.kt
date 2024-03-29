package com.yaoweather.android.logic.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @Description: 未来几天天气数据
 * @author: yaoyifei
 * @CreateDate: 2021/8/16 23:00
 */

data class DailyResponse(val status: String, val result: Result) {
    data class Result(val daily: Daily)
    data class Daily(
        val skycon: List<Skycon>,
        val temperature: List<Temperature>,
        @SerializedName("life_index") val lifeIndex: LifeIndex
    )

    data class LifeIndex(
        val coldRisk: List<LifeDescription>,
        val carWashing: List<LifeDescription>,
        val ultraviolet: List<LifeDescription>,
        val dressing: List<LifeDescription>
    )

    data class LifeDescription(val desc: String)

    data class Skycon(val value: String, val date: Date)

    data class Temperature(val max: Float, val min: Float)

    data class AQI(val chn: Float)
}