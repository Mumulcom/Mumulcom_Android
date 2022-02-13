package com.example.mumulcom

import com.example.mumulcom.data.Question

interface TabConceptQuestionView {
    fun onGetConceptQuestionsLoading()
    fun onGetConceptQuestionsSuccess(result: ArrayList<Question>?)
    fun onGetConceptQuestionsFailure(code:Int, message:String)
}