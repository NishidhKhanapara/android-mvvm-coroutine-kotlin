package com.nishidhpatel.mvvm.Repository

import com.nishidhpatel.mvvm.Api.Apis
import com.nishidhpatel.mvvm.Api.UsesCaseResult
import com.nishidhpatel.mvvm.models.Museums.MuseumsResponse


interface MuseumsListRepository {
    suspend fun getList(): UsesCaseResult<MuseumsResponse>
}

class MuseumsListRepositoryImpl(private val apis: Apis): MuseumsListRepository {
    override suspend fun getList(): UsesCaseResult<MuseumsResponse> {
        val result = apis.getList().await()
        return try {
            UsesCaseResult.Success(result)
        }catch (e: Exception){
            UsesCaseResult.Failed(e)
        }
    }
}
