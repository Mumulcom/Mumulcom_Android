package com.example.mumulcom.view

import com.example.mumulcom.data.Comment


// 답변에 대한 댓글 호출시
interface CommentsForReplyView {
    fun onGetCommentsLoading()
    fun onGetCommentsSuccess(result : ArrayList<Comment>)
    fun onGetCommentsFailure(code:Int , message:String)
}