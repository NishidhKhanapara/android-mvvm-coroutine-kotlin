package com.nishidhpatel.mvvm.roomdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nishidhpatel.mvvm.models.Museums.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseViewModel(application: Application) :AndroidViewModel(application) {

    //storelist
    private var repository: DatabaseRepository? = null
    var allStoreList:LiveData<List<Data>> ?= null

    init {
        val databaseDao = AppRoomDataBase.getDatabase(application).databseDao()
        repository = DatabaseRepository(databaseDao)
        allStoreList=repository!!.storeList

    }

    fun insert(data: Data) = viewModelScope.launch(Dispatchers.IO) {
        repository!!.insert(data)
    }
}