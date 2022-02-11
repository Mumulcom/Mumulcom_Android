package com.example.mumulcom.view

import com.example.mumulcom.data.Question

interface CategoryQuestionView {
    fun onGetQuestionsLoading()
    fun onGetQuestionsSuccess(result: ArrayList<Question>?)
    fun onGetQuestionsFailure(code:Int, message:String)
}