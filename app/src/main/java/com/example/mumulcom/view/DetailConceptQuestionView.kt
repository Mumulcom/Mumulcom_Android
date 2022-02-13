package com.example.mumulcom.view

import com.example.mumulcom.data.DetailConceptQuestion


interface DetailConceptQuestionView {
    fun onGetDetailConceptQuestionsLoading()
    fun onGetDetailConceptQuestionsSuccess(result: ArrayList<DetailConceptQuestion>)
    fun onGetDetailConceptQuestionsFailure(code: Int, message: String)
}