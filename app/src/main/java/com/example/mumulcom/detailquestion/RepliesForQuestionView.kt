package com.example.mumulcom.detailquestion

// 질문에 대한 답변 interface (QuestionDetailActivity  에서 사용)
interface RepliesForQuestionView {
    fun onGetRepliesLoading()
    fun onGetRepliesSuccess(result : ArrayList<Reply>)
    fun onGetRepliesFailure(code:Int , message:String)
}