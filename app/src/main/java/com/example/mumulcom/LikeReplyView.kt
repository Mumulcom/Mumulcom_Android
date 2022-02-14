package com.example.mumulcom

import com.example.mumulcom.Like

interface LikeReplyView{
    fun onGetLikeReplyLoading()
    fun onGetLikeReplySuccess(result: Like)
    fun onGetLikeReplyFailure(code:Int, message:String)
}
