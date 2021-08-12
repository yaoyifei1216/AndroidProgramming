package com.yaoweather.android

import android.app.Application
import android.content.Context

class YaoWeatherApplication : Application() {


    companion object {
        lateinit var context: Context //全局context对象
        const val TOKEN = "0u4z3zLrU4A2k47B" //彩云天气给的token，用来获取返回的数据
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}