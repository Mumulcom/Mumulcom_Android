package com.example.mumulcom

import com.example.mumulcom.data.Question

interface TabCodingAnswerView {
    fun onGetCodingAnswersLoading()
    fun onGetCodingAnswersSuccess(result: ArrayList<Question>?)
    fun onGetCodingAnswersFailure(code:Int, message:String)
}