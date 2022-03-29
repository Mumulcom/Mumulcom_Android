package com.example.mumulcom


import android.util.Log
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CheckCodingQuestionService{
    private lateinit var checkCodingQuestionView: CheckCodingQuestionView

    fun setcheckcodingquestionView(checkCodingQuestionView: CheckCodingQuestionView){
        this.checkCodingQuestionView=checkCodingQuestionView
    }

<<<<<<< HEAD
    fun checkCodingQuestion(
        jwt: String,
        checkCoding: CheckCoding,
        images: List<MultipartBody.Part?>?
    ) {
        val checkCodingQuestionService =
            getRetrofit().create(CheckCodingQuestionRetrofitInterface::class.java)
//        userIdx: Long,
//        currentError: String,
//        myCodingSkill: String?,
//        bigCategoryIdx: Long,
//        smallCategoryIdx: Long?,
//        title: String,
//        codeQuestionUrl: String?
//        val jsonObject =
//            JSONObject("{\"userIdx\":${userIdx},\"currentError\":\"${currentError}\",\"myCodingSkill\":\"${myCodingSkill}\",\"bigCategoryIdx\":${bigCategoryIdx}, \"smallCategoryIdx\":${smallCategoryIdx},\"title\":\"${title}\",\"codeQuestionUrl\":\"${codeQuestionUrl}\"}").toString()
//
//        val CodeQuestionReq =
//            jsonObject.toRequestBody(contentType = "application/json".toMediaTypeOrNull())
//        Log.d("json/jsonObject", jsonObject)
//        Log.d("json/CodeQuestionReq", CodeQuestionReq.toString())
=======
    fun checkCodingQuestion(jwt: String, checkcoding: CheckCoding, images: ArrayList<MultipartBody.Part>?){
        val checkCodingQuestionService= getRetrofit().create(CheckCodingQuestionRetrofitInterface::class.java)
>>>>>>> aa13fb4ab1a7ff7c8c6fac83e4b6c28f8aa5c6c6

        checkCodingQuestionView.onCheckCodingQuestionLoading()

        checkCodingQuestionService.checkCodingQuestion(
            jwt, checkCoding, images
        ).enqueue(object : Callback<CheckCodingQuestionResponse> {
            override fun onResponse(
                call: Call<CheckCodingQuestionResponse>,
                response: Response<CheckCodingQuestionResponse>
            ) {
                Log.d("CHECKCODING/API-RESPONSE", response.toString())
                Log.d("CHECKCODING/API- response.body()", response.body().toString())
                Log.d("CHECKCODING/API- response.errorbody()", response.errorBody()!!.string())
                Log.d("CHECKCODING/API-RESPONSE3", response.isSuccessful.toString())
                Log.d("CHECKCODING/API-code", response.code().toString())
                Log.d("CHECKCODING/API-images", images.toString())
                Log.d("APIAPI", checkCodingQuestionService.checkCodingQuestion(
                    jwt, checkCoding, images
                ).request().toString())

                if (response.isSuccessful && response.code() == 200) {
                    val resp = response.body()!!
                    Log.d("CHECKCODING/API-SUCCESS-mumulcom", resp.toString())

                    when (resp.code) {
                        1000 -> checkCodingQuestionView.onCheckCodingQuestionSuccess(resp.result)
                        else -> checkCodingQuestionView.onCheckCodingQuestionFailure(
                            resp.code,
                            resp.message
                        )
                    }
                }
            }

            override fun onFailure(call: Call<CheckCodingQuestionResponse>, t: Throwable) {
                Log.d("CHECKCODING/API-ERROR", t.message.toString())

                checkCodingQuestionView.onCheckCodingQuestionFailure(400, "네트워크 오류가 발생했습니다.")
                Log.d("CHECKCODING/API-ERROR", t.message.toString())
            }
        })
        Log.d("CHECKCODING/API", "Hello")
        Log.d("API/images", images.toString())
        Log.d("API/jwt", jwt)
//        Log.d("API/CodeQuestionReq", CodeQuestionReq.toString())
//        Log.d("API/jsonObject", jsonObject.toString())
    }

}
