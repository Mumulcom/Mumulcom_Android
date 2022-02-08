package com.example.mumulcom.service

import android.annotation.SuppressLint
import android.util.Log
import com.example.mumulcom.*
import com.example.mumulcom.dataclass.User
import com.example.mumulcom.retrofit.AuthRetrofitInterface
import com.example.mumulcom.view.LoginView
import com.example.mumulcom.view.SignUpView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {
    private lateinit var signUpView: SignUpView
    private lateinit var loginView: LoginView

    fun setSignUpView(signUpView: SignUpView) {
        this.signUpView = signUpView
    }

    fun setLoginView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun signUp(user: User) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        signUpView.onLoginLoading()

        authService.signUp(user).enqueue(object : Callback<AuthResponse> {
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("SIGNUPACT/API-RESPONSE", response.toString())

                if(response.isSuccessful && response.code() == 200) {
                    val resp = response.body()!!
                    Log.d("SIGNUPACT/API-RESPONSE-FLO", resp.toString())

                    when(resp.code) {
                        1000 -> signUpView.onSignUpSuccess()
                        else -> signUpView.onSignUpFailure(resp.code, resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("SIGNUPACT/API-ERROR", t.toString())
                //signUpView.onSignUpFailure(400, t.message.toString())
                signUpView.onSignUpFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })
    }

    fun login(user: User) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        loginView.onLoginLoading()

        authService.login(user).enqueue(object : Callback<AuthResponse>{
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.d("LOGINACT/API-RESPONSE", response.toString())

                if(response.isSuccessful && response.code() == 200) {
                    val resp = response.body()!!
                    Log.d("LOGINACT/API-RESPONSE-FLO", resp.toString())

                    when(resp.code) {
                        1000 -> loginView.onLoginSuccess(resp.result!!)
                        else -> loginView.onLoginFailure(resp.code, resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("LOGINACT/API-ERROR", t.toString())

                loginView.onLoginFailure(400, "네트워크 오류가 발생했습니다.")
            }
        })
    }
}