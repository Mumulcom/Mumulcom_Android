package com.example.mumulcom.myquestion

import com.example.mumulcom.Question

interface TabCodingAnswerView {
    fun onGetCodingAnswersLoading()
    fun onGetCodingAnswersSuccess(result: ArrayList<Question>?)
    fun onGetCodingAnswersFailure(code:Int, message:String)
}