package com.example.mumulcom

import com.example.mumulcom.data.CodingQuestion

interface SearchCodingQuestionView {
    fun onGetCodingQuestionsLoading()
    fun onGetCodingQuestionsSuccess(result: ArrayList<CodingQuestion>?)
    fun onGetCodingQuestionsFailure(code:Int, message:String)
}