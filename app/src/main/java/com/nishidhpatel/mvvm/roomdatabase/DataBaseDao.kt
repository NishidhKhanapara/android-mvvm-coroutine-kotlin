package com.nishidhpatel.mvvm.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nishidhpatel.mvvm.models.Museums.Data

@Dao
interface DataBaseDao
{

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insert(data: Data)

    @Query("SELECT * from list")
    fun getList():LiveData<List<Data>>

    @Query(value = "select * from list")
    fun allUsers() : List<Data>



}