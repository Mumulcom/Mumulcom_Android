package com.example.mumulcom.category

import com.example.mumulcom.Question

interface CategoryQuestionView {
    fun onGetQuestionsLoading()
    fun onGetQuestionsSuccess(result: ArrayList<Question>?)
    fun onGetQuestionsFailure(code:Int, message:String)
}