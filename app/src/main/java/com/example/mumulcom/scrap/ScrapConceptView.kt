package com.example.mumulcom.scrap

import com.example.mumulcom.Question

interface ScrapConceptView {
    fun onGetScrapConceptLoading()
    fun onGetScrapConceptSuccess(result: ArrayList<Question>?)
    fun onGetScrapConceptFailure(code:Int, message:String)
}