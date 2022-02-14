package com.example.mumulcom.detailquestion




interface DetailConceptQuestionView {
    fun onGetDetailConceptQuestionsLoading()
    fun onGetDetailConceptQuestionsSuccess(result: ArrayList<DetailConceptQuestion>)
    fun onGetDetailConceptQuestionsFailure(code: Int, message: String)
}