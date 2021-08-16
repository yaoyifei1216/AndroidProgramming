package com.yaoweather.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.yaoweather.android.logic.Repository
import com.yaoweather.android.logic.model.Place

/**
 * @Description: ViewModel层
 * @author: yaoyifei
 * @CreateDate: 2021/8/12 23:47
 */
class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>()
    val placeLiceData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place);

    fun getSavedPlace() = Repository.getSavedPlace()

    fun isPlaceSaved() = Repository.isPlaceSaved()
}