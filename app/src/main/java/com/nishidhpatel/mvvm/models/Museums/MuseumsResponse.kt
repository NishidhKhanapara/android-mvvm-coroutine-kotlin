package com.nishidhpatel.mvvm.models.Museums


import com.google.gson.annotations.SerializedName

data class MuseumsResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
)