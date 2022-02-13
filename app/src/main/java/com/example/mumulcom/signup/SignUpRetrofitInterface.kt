package com.example.mumulcom.signup

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpRetrofitInterface {
    @POST("/users")  // 회원 가입
    fun signUp(@Body user: User): Call<SignUpResponse>
}