package com.example.mumulcom.detailquestion

import com.example.mumulcom.detailquestion.DetailCodingQuestion


interface DetailCodingQuestionView {
    fun onGetDetailCodingQuestionsLoading()
    fun onGetDetailCodingQuestionsSuccess(result: ArrayList<DetailCodingQuestion>)
    fun onGetDetailCodingQuestionsFailure(code:Int, message:String)
}