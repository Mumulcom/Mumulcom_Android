package com.example.mumulcom.search

interface SearchCodingQuestionView {
    fun onGetCodingQuestionsLoading()
    fun onGetCodingQuestionsSuccess(result: ArrayList<CodingQuestion>?)
    fun onGetCodingQuestionsFailure(code:Int, message:String)
}