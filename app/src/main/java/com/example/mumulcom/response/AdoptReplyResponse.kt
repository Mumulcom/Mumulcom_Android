package com.example.mumulcom.response

import com.example.mumulcom.data.Adopt
import com.example.mumulcom.data.Question
import com.google.gson.annotations.SerializedName

data class AdoptReplyResponse(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code:Int,
    @SerializedName("message") val message:String,
    @SerializedName("result") val result: Adopt
)