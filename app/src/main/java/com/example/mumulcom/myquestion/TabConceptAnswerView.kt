package com.example.mumulcom.myquestion

import com.example.mumulcom.Question

interface TabConceptAnswerView {
    fun onGetConceptAnswersLoading()
    fun onGetConceptAnswersSuccess(result: ArrayList<Question>?)
    fun onGetConceptAnswersFailure(code:Int, message:String)
}