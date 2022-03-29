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
<<<<<<< HEAD
        @Part images: List<MultipartBody.Part?>?,

=======
        @Part images: ArrayList<MultipartBody.Part>?
//        @Body checkCoding: CheckCoding,
>>>>>>> aa13fb4ab1a7ff7c8c6fac83e4b6c28f8aa5c6c6
    ): Call<CheckCodingQuestionResponse>

}




//@Header("X-ACCESS-TOKEN") jwt : String, // jwt
//@Path("userIdx") userIdx : Long,