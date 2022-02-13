package com.example.mumulcom.view

import com.example.mumulcom.data.Adopt


interface AdoptReplyView {
    fun onGetAdoptReplyLoading()
    fun onGetAdoptReplySuccess(result: Adopt)
    fun onGetAdoptReplyFailure(code:Int, message:String)
}