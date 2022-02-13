package com.example.mumulcom.detailquestion

interface UploadCommentView {
    fun onGetUploadCommentLoading()
    fun onGetUploadCommentSuccess(result: Like)
    fun onGetUploadCommentFailure(code:Int, message:String)
}