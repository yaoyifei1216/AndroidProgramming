package com.yaoweather.android.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.yaoweather.android.YaoWeatherApplication
import com.yaoweather.android.logic.model.Place

/**
 * @Description: 城市数据持久化单例类
 * @author: yaoyifei
 * @CreateDate: 2021/8/17 1:09
 */
object PlaceDao {

    fun savePlace(place: Place) {
        sharedPreferences().edit() {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() = YaoWeatherApplication.context.getSharedPreferences(
        "yao_weather",
        Context.MODE_PRIVATE
    )
}