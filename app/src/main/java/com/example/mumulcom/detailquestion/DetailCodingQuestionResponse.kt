package com.example.mumulcom.detailquestion

import com.example.mumulcom.detailquestion.DetailCodingQuestion
import com.google.gson.annotations.SerializedName

class DetailCodingQuestionResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code:Int,
    @SerializedName("message") val message:String,
    @SerializedName("result") val result: ArrayList<DetailCodingQuestion>
)