package com.example.mumulcom.detailquestion


interface AdoptReplyView {
    fun onGetAdoptReplyLoading()
    fun onGetAdoptReplySuccess(result: Adopt)
    fun onGetAdoptReplyFailure(code:Int, message:String)
}