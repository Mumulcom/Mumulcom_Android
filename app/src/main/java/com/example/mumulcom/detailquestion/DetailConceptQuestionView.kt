package com.example.mumulcom.detailquestion

import com.example.mumulcom.detailquestion.DetailConceptQuestion


interface DetailConceptQuestionView {
    fun onGetDetailConceptQuestionsLoading()
    fun onGetDetailConceptQuestionsSuccess(result: ArrayList<DetailConceptQuestion>)
    fun onGetDetailConceptQuestionsFailure(code: Int, message: String)
}