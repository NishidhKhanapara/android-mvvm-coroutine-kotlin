package com.nishidhpatel.mvvm.roomdatabase

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.nishidhpatel.mvvm.models.Museums.Data

class DatabaseRepository(private val dataBaseDao: DataBaseDao)
{

    val storeList:LiveData<List<Data>> = dataBaseDao.getList()

    @WorkerThread
    suspend fun insert(data: Data){
        dataBaseDao.insert(data)
    }

}