package com.example.mumulcom

open class AlarmListItem(
    val itemType: Int
) {
    companion object {
        const val TYPE_DATE = 0
        const val TYPE_GENERAL = 1
    }
}