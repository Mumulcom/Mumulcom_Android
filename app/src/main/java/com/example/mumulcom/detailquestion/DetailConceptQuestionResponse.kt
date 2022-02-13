package com.example.mumulcom.detailquestion


import com.example.mumulcom.detailquestion.DetailConceptQuestion
import com.google.gson.annotations.SerializedName

class DetailConceptQuestionResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code:Int,
    @SerializedName("message") val message:String,
    @SerializedName("result") val result: ArrayList<DetailConceptQuestion>
)