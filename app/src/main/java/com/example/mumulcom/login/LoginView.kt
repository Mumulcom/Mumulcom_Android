package com.example.mumulcom.login

interface LoginView {
    fun onLoginLoading()
    fun onLoginSuccess(auth: Auth)
    fun onLoginFailure(code:Int, message:String)
}