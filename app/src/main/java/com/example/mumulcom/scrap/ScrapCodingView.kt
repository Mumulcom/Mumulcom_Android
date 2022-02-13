package com.example.mumulcom.scrap

import com.example.mumulcom.Question

interface ScrapCodingView {
    fun onGetScrapCodingLoading()
    fun onGetScrapCodingSuccess(result: ArrayList<Question>?)
    fun onGetScrapCodingFailure(code:Int, message:String)
}