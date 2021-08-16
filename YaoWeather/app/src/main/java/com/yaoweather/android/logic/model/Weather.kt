package com.yaoweather.android.logic.model

/**
 * @Description: 天气类，包括实时天气和天气数据
 * @author: yaoyifei
 * @CreateDate: 2021/8/16 23:11
 */
data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)