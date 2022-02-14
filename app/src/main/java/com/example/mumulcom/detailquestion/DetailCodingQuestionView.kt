package com.example.mumulcom.detailquestion




interface DetailCodingQuestionView {
    fun onGetDetailCodingQuestionsLoading()
    fun onGetDetailCodingQuestionsSuccess(result: ArrayList<DetailCodingQuestion>)
    fun onGetDetailCodingQuestionsFailure(code:Int, message:String)
}