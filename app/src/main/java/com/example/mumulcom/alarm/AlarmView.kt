package com.example.mumulcom.alarm

import com.example.mumulcom.alarm.Alarm

interface AlarmView {
    fun onGetAlarmLoading()
    fun onGetAlarmSuccess(result: ArrayList<Alarm>?)
    fun onGetAlarmFailure(code:Int, message:String)
}