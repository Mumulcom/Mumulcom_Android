package com.example.mumulcom.view

import com.example.mumulcom.data.Like

interface LikeReplyView{
    fun onGetLikeReplyLoading()
    fun onGetLikeReplySuccess(result: Like)
    fun onGetLikeReplyFailure(code:Int, message:String)
}
