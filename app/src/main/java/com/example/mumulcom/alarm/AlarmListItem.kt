package com.example.mumulcom.alarm

open class AlarmListItem(
    val type: Int
) {
    companion object {
        const val TYPE_DATE = 0
        const val TYPE_GENERAL = 1
    }
}