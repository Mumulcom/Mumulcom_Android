package com.example.mumulcom.category

import com.example.mumulcom.Question
import com.google.gson.annotations.SerializedName

data class CategoryQuestionResponse(
    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code:Int,
    @SerializedName("message") val message:String,
    @SerializedName("result") val result: ArrayList<Question>?
)