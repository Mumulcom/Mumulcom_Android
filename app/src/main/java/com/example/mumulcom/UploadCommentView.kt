package com.example.mumulcom

import com.example.mumulcom.Like

interface UploadCommentView {
    fun onGetUploadCommentLoading()
    fun onGetUploadCommentSuccess(result: Like)
    fun onGetUploadCommentFailure(code:Int, message:String)
}