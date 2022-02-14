package com.example.mumulcom

import com.example.mumulcom.Like

interface LikeQuestionView {
    fun onGetLikeQuestionLoading()
    fun onGetLikeQuestionSuccess(result: Like)
    fun onGetLikeQuestionFailure(code:Int, message:String)
}