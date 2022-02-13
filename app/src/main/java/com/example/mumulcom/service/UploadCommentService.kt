package com.example.mumulcom.service

import com.example.mumulcom.data.CommentSend
import com.example.mumulcom.getRetrofit
import com.example.mumulcom.response.UploadCommentResponse
import com.example.mumulcom.retrofit.QuestionRetrofitInterface
import com.example.mumulcom.view.UploadCommentView
import retrofit2.Call
import retrofit2.Response

class UploadCommentService {
    private lateinit var uploadCommentView: UploadCommentView


    // 외부 접근
    fun setUploadCommentView(uploadCommentView: UploadCommentView){
        this.uploadCommentView = uploadCommentView
    }

    fun getUploadComment(jwt:String,commentSend: CommentSend){
        val uploadCommentService = getRetrofit().create(QuestionRetrofitInterface::class.java)

        uploadCommentView.onGetUploadCommentLoading()

        uploadCommentService.getUploadComment(jwt,commentSend)
            .enqueue(object : retrofit2.Callback<UploadCommentResponse>{
                override fun onResponse(call: Call<UploadCommentResponse>, response: Response<UploadCommentResponse>) {

                    val resp = response.body()!!

                    when(resp.code){
                        1000-> uploadCommentView.onGetUploadCommentSuccess(resp.result)
                        else-> uploadCommentView.onGetUploadCommentFailure(resp.code,resp.message)
                    }

                }

                override fun onFailure(call: Call<UploadCommentResponse>, t: Throwable) {
                    uploadCommentView.onGetUploadCommentFailure(400,"네트워크 오류 ")
                }

            })
    }
}