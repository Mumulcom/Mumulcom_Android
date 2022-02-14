package com.example.mumulcom

import com.example.mumulcom.Question
import com.google.gson.annotations.SerializedName

data class ScrapResponse(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code:Int,
    @SerializedName("message") val message:String,
    @SerializedName("result") val result: ArrayList<Question>?
)