package com.example.mumulcom.alarm

import com.google.gson.annotations.SerializedName

data class Alarm(
    @SerializedName("questionIdx") val questionIdx: Long, // 질문 고유 번호
    @SerializedName("profileImgUrl") val profileImgUrl: String, // 유저 프로필 url
    @SerializedName("content") val content: String, // 알림 내용
    @SerializedName("diffTime") val diffTime: String, // 날짜 표현

    // @SerializedName("bigCategoryName") val bigCategoryName: String, // 상위 카테고리
    // @SerializedName("type") val type: String, // 질문 유형 (1 -> 코딩 질문 / 2 -> 개념 질문)
)
