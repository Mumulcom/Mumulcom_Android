package com.example.mumulcom.response

import com.example.mumulcom.dataclass.Question
import com.example.mumulcom.dataclass.Reply
import com.google.gson.annotations.SerializedName

data class RepliesForQuestionResponse(

    @SerializedName("isSuccess") val isSuccess : Boolean,
    @SerializedName("code") val code:Int,
    @SerializedName("message") val message:String,
    @SerializedName("result") val result: ArrayList<Reply>

)