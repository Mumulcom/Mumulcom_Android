package com.example.mumulcom

import com.example.mumulcom.data.Question

interface ScrapCodingView {
    fun onGetScrapCodingLoading()
    fun onGetScrapCodingSuccess(result: ArrayList<Question>?)
    fun onGetScrapCodingFailure(code:Int, message:String)
}