package com.example.mumulcom.signup

import com.google.gson.annotations.SerializedName

data class SignUpNicknameResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code:Int,
    @SerializedName("message") val message:String,
    @SerializedName("result") val result: Boolean,
)