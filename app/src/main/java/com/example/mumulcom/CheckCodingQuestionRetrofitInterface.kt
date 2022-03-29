package com.example.mumulcom

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.util.ArrayList


//인터페이스 생성
interface CheckCodingQuestionRetrofitInterface {
    @Multipart
    @POST("/questions/coding")
    fun checkCodingQuestion(
        @Header("X-ACCESS-TOKEN") X_ACCESS_TOKEN: String,
        @Part("CodeQuestionReq") CodeQuestionReq: CheckCoding,
        @Part images: List<MultipartBody.Part?>?,

    ): Call<CheckCodingQuestionResponse>

}




//@Header("X-ACCESS-TOKEN") jwt : String, // jwt
//@Path("userIdx") userIdx : Long,