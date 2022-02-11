package com.example.mumulcom.view

import com.example.mumulcom.data.DetailCodingQuestion


interface DetailCodingQuestionView {
    fun onGetDetailCodingQuestionsLoading()
    fun onGetDetailCodingQuestionsSuccess(result: ArrayList<DetailCodingQuestion>)
    fun onGetDetailCodingQuestionsFailure(code:Int, message:String)
}