package com.nishidhpatel.mvvm.mvvm.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nishidhpatel.mvvm.Api.UsesCaseResult
import com.nishidhpatel.mvvm.Repository.MuseumsListRepository
import com.nishidhpatel.mvvm.models.Museums.MuseumsResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


open class MuseumListViewModel(private val storeListRepository: MuseumsListRepository): ViewModel(), CoroutineScope {
    val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showloding = MutableLiveData<Boolean>()
    val storeData = MutableLiveData<MuseumsResponse>()
    val showerror = MutableLiveData<String>()



    fun getList(){

        launch {
            val result = withContext(Dispatchers.IO) {
                storeListRepository.getList()
            }

            showloding.value = false
            when(result){
                is UsesCaseResult.Success -> {
                    withContext(Dispatchers.Main){
                        storeData.value= result.data
                    }
                }
                is UsesCaseResult.Failed -> {
                    showerror.value = result.exception.message
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}