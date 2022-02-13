package com.example.mumulcom.detailquestion

interface LikeReplyView{
    fun onGetLikeReplyLoading()
    fun onGetLikeReplySuccess(result: Like)
    fun onGetLikeReplyFailure(code:Int, message:String)
}
