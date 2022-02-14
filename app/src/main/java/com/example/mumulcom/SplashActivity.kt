package com.example.mumulcom

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mumulcom.databinding.ActivitySplashBinding
import com.kakao.sdk.user.UserApiClient

class SplashActivity : AppCompatActivity(), LoginView {
    lateinit var binding:ActivitySplashBinding

    private var email: String = ""  // 유저 이메일

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 카카오에서 사용자 email 받아오기
        //getUserInfo()
        // 사용자 정보 요청
        UserApiClient.instance.me { kakaoUser, error ->
            if (error != null) {
                Log.e(ContentValues.TAG, "사용자 정보 요청 실패", error)
            }
            else if (kakaoUser != null) {
                email = kakaoUser.kakaoAccount?.email.toString()
                Log.i(ContentValues.TAG, "사용자 정보 요청 성공 - 이메일: $email")
                // 로그인
                login(email)
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            if (email.isEmpty()) {  // 이메일이 비어있다면 -> 카카오 로그인 또는 회원가입 진행해야함
                Log.d(ContentValues.TAG, "LoginActivity 실행")
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {    // 이메일 값이 있으면
                Log.d(ContentValues.TAG, "MainActivity 실행")
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } }, 3000) //1초가 1000
    }

    private fun login(email: String) {
        val login = Login(email)

        val loginService = LoginService()
        loginService.setLoginView(this)
        loginService.login(login)
    }

    override fun onLoginLoading() {
        Log.d("Login/API","로그인 로딩 중...")
    }

    override fun onLoginSuccess(profile: Profile) {
        if (profile != null) {
            saveJwt(this, profile.jwt)
            saveUserIdx(this, profile.userIdx)
            saveEmail(this, profile.email)
            saveName(this, profile.name)
            saveNickname(this, profile.nickname)
            saveGroup(this, profile.group)
            profile.myCategories?.let { saveCategories(this, it) }
            saveProfileImgUrl(this, profile.profileImgUrl)

            Log.d("jwt", profile.jwt)
            Log.d("userIdx", profile.userIdx.toString())
            Log.d("email", profile.email)
            Log.d("name", profile.name)
            Log.d("nickname", profile.nickname)
            Log.d("group", profile.group)
            Log.d("myCategories", profile.myCategories.toString())
            Log.d("profileImgUrl", profile.profileImgUrl)
        }
    }

    override fun onLoginFailure(code: Int, message: String) {
        when(code) {
            3014 -> {   // DB에 없는 이메일일 경우
                Log.d("SingUpCategoryActivity/API", message)
            }
            400 -> {
                Log.d("SingUpCategoryActivity/API", message)
            }
        }
    }
}