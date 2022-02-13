package com.example.mumulcom.view

import com.example.mumulcom.data.Like

interface UploadCommentView {
    fun onGetUploadCommentLoading()
    fun onGetUploadCommentSuccess(result: Like)
    fun onGetUploadCommentFailure(code:Int, message:String)
}