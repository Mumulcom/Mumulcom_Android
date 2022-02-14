package com.example.mumulcom

import com.example.mumulcom.Question

interface TabConceptQuestionView {
    fun onGetConceptQuestionsLoading()
    fun onGetConceptQuestionsSuccess(result: ArrayList<Question>?)
    fun onGetConceptQuestionsFailure(code:Int, message:String)
}