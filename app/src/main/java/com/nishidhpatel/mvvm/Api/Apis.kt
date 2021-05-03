package com.nishidhpatel.mvvm.Api

import com.nishidhpatel.mvvm.Utils.Constans_List
import com.nishidhpatel.mvvm.models.Museums.MuseumsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Apis {


    @GET(Constans_List)
    fun getList(): Deferred<MuseumsResponse>


}