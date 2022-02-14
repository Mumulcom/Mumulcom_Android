package com.example.mumulcom

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {
    @POST("/users/login")  // 로그인
    fun login(@Body email: Login): Call<LoginResponse>
}