package com.yaoweather.android.logic

import androidx.lifecycle.liveData
import com.yaoweather.android.logic.model.Place
import com.yaoweather.android.logic.network.YaoWeatherNetwork
import kotlinx.coroutines.Dispatchers

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
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = YaoWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("placeResponse status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}