package com.example.mumulcom

import com.example.mumulcom.data.ConceptQuestion

interface SearchConceptQuestionView {
    fun onGetConceptQuestionsLoading()
    fun onGetConceptQuestionsSuccess(result: ArrayList<ConceptQuestion>?)
    fun onGetConceptQuestionsFailure(code:Int, message:String)
}