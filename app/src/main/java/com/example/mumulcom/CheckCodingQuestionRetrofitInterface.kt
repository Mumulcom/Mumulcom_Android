package com.example.mumulcom

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


//인터페이스 생성
interface CheckCodingQuestionRetrofitInterface {

    @Headers("Content-Type: multipart/form-data")
    @Multipart
    @POST("/questions/coding")
    fun checkCodingQuestion(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Part images: MultipartBody.Part,
        @Part codeQuestionReq: MultipartBody.Part,

        ): Call<CheckCodingQuestionResponse>
}