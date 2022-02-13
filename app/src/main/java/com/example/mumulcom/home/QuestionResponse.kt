package com.example.mumulcom.home

import com.example.mumulcom.Question
import com.google.gson.annotations.SerializedName

data class QuestionResponse(

    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code:Int,
    @SerializedName("message") val message:String,
    @SerializedName("result") val result: ArrayList<Question> // 빈 배열로라도 들어옴.

)