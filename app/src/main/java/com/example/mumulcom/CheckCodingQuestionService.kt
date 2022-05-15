package com.example.mumulcom


import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class CheckCodingQuestionService{
    private lateinit var checkCodingQuestionView: CheckCodingQuestionView

    fun setcheckcodingquestionView(checkCodingQuestionView: CheckCodingQuestionView){
        this.checkCodingQuestionView=checkCodingQuestionView
    }

    fun checkCodingQuestion(jwt: String, codingQuestionSend:CodingQuestionSend, body: ArrayList<MultipartBody.Part>?
    ) {
        val checkCodingQuestionService= getRetrofit().create(CheckCodingQuestionRetrofitInterface::class.java)

        Log.d("kim","코딩 질문 서비스 실행")

        checkCodingQuestionView.onCheckCodingQuestionLoading()

        checkCodingQuestionService.checkCodingQuestion(jwt, codingQuestionSend,body)
            .enqueue(object : Callback<CheckCodingQuestionResponse> {
            override fun onResponse(call: Call<CheckCodingQuestionResponse>, response: Response<CheckCodingQuestionResponse>) {

                Log.d("kim","서버 연결 성공")

                val resp = response.body()

                Log.d("kim",response.toString())

//                when(resp.code){
//                    1000-> checkCodingQuestionView.onCheckCodingQuestionSuccess(resp.result)
//                    else->checkCodingQuestionView.onCheckCodingQuestionFailure(resp.code, resp.message)
//                }


//                if (response.isSuccessful&&response.code()==200){
//                    val resp=response.body()!!
//                    Log.d("CHECKCODING/API-SUCCESS-mumulcom", resp.toString())
//
//                    when(resp.code){
//                        1000-> checkCodingQuestionView.onCheckCodingQuestionSuccess(resp.result)
//
//                        else->checkCodingQuestionView.onCheckCodingQuestionFailure(resp.code, resp.message)
//                    }
//                }
            }

            override fun onFailure(call: Call<CheckCodingQuestionResponse>, t: Throwable) {
                Log.d("CHECKCODING/API-ERROR",t.message.toString())

                checkCodingQuestionView.onCheckCodingQuestionFailure(400, "네트워크 오류가 발생했습니다.")
                Log.d("CHECKCODING/API-ERROR",t.message.toString())
            }
        })

    }

}
