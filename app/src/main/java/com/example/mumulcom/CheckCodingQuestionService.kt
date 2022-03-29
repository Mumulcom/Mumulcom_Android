package com.example.mumulcom


import android.util.Log
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

    fun checkCodingQuestion(jwt: String, images: ArrayList<MultipartBody.Part?>?, checkCoding: CheckCoding
    ) {
        val checkCodingQuestionService= getRetrofit().create(CheckCodingQuestionRetrofitInterface::class.java)

//        val jsonObject = JSONObject("{\"userIdx\":${userIdx},\"currentError\":\"${currentError}\",\"myCodingSkill\":\"${myCodingSkill}\",\"bigCategoryIdx\":${bigCategoryIdx}, \"smallCategoryIdx\":${smallCategoryIdx},\"title\":\"${title}\",\"codeQuestionUrl\":\"${codeQuestionUrl}\"}").toString()
////        val jsonBody = RequestBody.create("application/json".toMediaTypeOrNull(),jsonObject)
//        val CodeQuestionReq = jsonObject.toRequestBody(contentType = "application/json".toMediaTypeOrNull())
//        Log.d("json/jsonObject", jsonObject)
//        Log.d("json/jsonBody", CodeQuestionReq.toString())
//
//        val body = "multipart/form-data".toRequestBody(MultipartBody.FORM)
//        val emptyPart = MultipartBody.Part.createFormData("images","images",body)
//        val emptyList = arrayListOf<MultipartBody.Part?>()
//        emptyList.add(emptyPart)


        checkCodingQuestionView.onCheckCodingQuestionLoading()
        checkCodingQuestionService.checkCodingQuestion(
            jwt, images, checkCoding
        ).enqueue(object : Callback<CheckCodingQuestionResponse> {
            override fun onResponse(call: Call<CheckCodingQuestionResponse>, response: Response<CheckCodingQuestionResponse>) {
                Log.d("CHECKCODING/API-RESPONSE", response.toString())
                Log.d("CHECKCODING/API- response.body()", response.body().toString())
                Log.d("CHECKCODING/API- response.errorbody()", response.errorBody()!!.string())
                Log.d("CHECKCODING/API-RESPONSE3", response.isSuccessful.toString())
                Log.d("CHECKCODING/API-images", images.toString())
                Log.d("CHECKCODING/API-body", checkCodingQuestionService.checkCodingQuestion(
                    jwt, images, checkCoding
                ).request().toString())

                if (response.isSuccessful&&response.code()==200){
                    val resp=response.body()!!
                    Log.d("CHECKCODING/API-SUCCESS-mumulcom", resp.toString())

                    when(resp.code){
                        1000-> checkCodingQuestionView.onCheckCodingQuestionSuccess(resp.result)

                        else->checkCodingQuestionView.onCheckCodingQuestionFailure(resp.code, resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<CheckCodingQuestionResponse>, t: Throwable) {
                Log.d("CHECKCODING/API-ERROR",t.message.toString())

                checkCodingQuestionView.onCheckCodingQuestionFailure(400, "네트워크 오류가 발생했습니다.")
                Log.d("CHECKCODING/API-ERROR",t.message.toString())
            }
        })
        Log.d("CHECKCODING/API","Hello")
        Log.d("API/images", images.toString())
        Log.d("API/jwt", jwt)
//        Log.d("API/CodeQuestionReq", CodeQuestionReq.toString())
//        Log.d("API/jsonObject", jsonObject.toString())
    }

}
