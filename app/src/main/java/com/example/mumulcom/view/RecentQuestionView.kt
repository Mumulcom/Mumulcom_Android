package com.example.mumulcom.view

import com.example.mumulcom.data.Question


// 최근 질문 4개 에 대한 interface (HomeFragment에서 사용)
interface RecentQuestionView {
    fun onGetQuestionsLoading()
    fun onGetQuestionsSuccess(result: ArrayList<Question>?)
    fun onGetQuestionsFailure(code:Int, message:String)
}