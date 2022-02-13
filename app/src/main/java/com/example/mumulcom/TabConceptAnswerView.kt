package com.example.mumulcom

import com.example.mumulcom.data.Question

interface TabConceptAnswerView {
    fun onGetConceptAnswersLoading()
    fun onGetConceptAnswersSuccess(result: ArrayList<Question>?)
    fun onGetConceptAnswersFailure(code:Int, message:String)
}