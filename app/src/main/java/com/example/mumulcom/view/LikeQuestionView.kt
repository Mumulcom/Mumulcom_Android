package com.example.mumulcom.view

import com.example.mumulcom.data.Like

interface LikeQuestionView {
    fun onGetLikeQuestionLoading()
    fun onGetLikeQuestionSuccess(result: Like)
    fun onGetLikeQuestionFailure(code:Int, message:String)
}