package com.example.mumulcom

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mumulcom.databinding.ActivityMainBinding
import com.example.mumulcom.home.HomeFragment
import com.example.mumulcom.login.Auth
import com.example.mumulcom.login.Login
import com.example.mumulcom.login.LoginService
import com.example.mumulcom.login.LoginView
import com.example.mumulcom.myquestion.QuestionFragment
import com.example.mumulcom.profile.ProfileFragment
import com.kakao.sdk.user.UserApiClient

// TODO 하단 프로필 사진 연동하기 -> 바텀 네비에 서버에서 받은 값이 들어갈 수 있는가...?
class MainActivity : AppCompatActivity(), LoginView {
    lateinit var binding: ActivityMainBinding

    private var email: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 카카오에서 사용자 email 받아오기
        //getUserInfo()
        // 사용자 정보 요청
        UserApiClient.instance.me { kakaoUser, error ->
            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)
            }
            else if (kakaoUser != null) {
                email = kakaoUser.kakaoAccount?.email.toString()
                Log.i(TAG, "사용자 정보 요청 성공 - 이메일: $email")
                // 로그인
                Log.d(TAG, "login 함수 실행")
                login(email)
            }
        }

        initNavigation()

        binding.mainBnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.scrapFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ScrapFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.questionFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, QuestionFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.alarmFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, AlarmFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.profileFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ProfileFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

            }
            false
        }
    }

    private fun initNavigation() {
        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()
    }

    private fun login(email: String) {
        val login = Login(email)

        val loginService = LoginService()
        loginService.setLoginView(this)
        loginService.login(login)
    }

    override fun onLoginLoading() {
        binding.mainLoginLoadingPb.visibility = View.VISIBLE
        Log.d("Login/API","로그인 로딩 중...")
    }

    override fun onLoginSuccess(auth: Auth) {
        binding.mainLoginLoadingPb.visibility = View.GONE

        if (auth != null) {
            saveJwt(this, auth.jwt)
            saveUserIdx(this, auth.userIdx)

            Log.d("jwt", auth.jwt)
            Log.d("userIdx", auth.userIdx.toString())
        }
    }

    override fun onLoginFailure(code: Int, message: String) {
        binding.mainLoginLoadingPb.visibility = View.GONE

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

