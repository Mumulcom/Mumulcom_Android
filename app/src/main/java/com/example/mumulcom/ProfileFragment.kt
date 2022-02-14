package com.example.mumulcom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mumulcom.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(), ProfileView {
    lateinit var binding: FragmentProfileBinding

    private var jwt: String = ""    // jwt
    private var userIdx: Long = 0   // 유저 인덱스
    private var email: String = ""  // 유저 이메일
    private var name: String = ""   // 유저 이름
    private var nickname: String = ""   // 유저 닉네임
    private var group: String = ""  // 유저 소속
    private var myCategories: MutableList<String> = mutableListOf() // 유저 관심 카테고리
    private var profileImgUrl: String = ""  // 유저 프로필 이미지


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        jwt = context?.let { getJwt(it) }.toString()
        userIdx = context?.let { getUserIdx(it) }!!

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        getProfile(jwt, userIdx)
    }

    private fun initView() {
        context?.let { Glide.with(it).load(profileImgUrl).into(binding.profileProfileIv) }
        binding.profileNicknameTv.text = nickname
        binding.profileGroupTv.text = group
    }

    private fun getProfile(jwt: String, userIdx: Long){
        val profileService = ProfileService()
        profileService.setProfileService(this)

        profileService.getProfiles(jwt, userIdx)
    }

    override fun onGetProfileLoading() {
        Log.d("Profile/API","로딩중...")
    }

    override fun onGetProfileSuccess(result: Profile) {
        Log.d("Profile/API","성공")
        if (result != null) {   // 회원 정보가 있으면
            // SharedPreference에 저장한 정보 불러오기
            email = context?.let { getEmail(it) }.toString()
            name = context?.let { getName(it) }.toString()
            nickname = context?.let { getNickname(it) }.toString()
            group = context?.let { getGroup(it) }.toString()
            myCategories = context?.let { getCategories(it) }!!
            profileImgUrl = context?.let { getProfileImgUrl(it) }.toString()

            initView()
        }
    }

    override fun onGetProfileFailure(code: Int, message: String) {
        when (code) {
            400 -> Log.d("Profile/API", message)
        }
    }
}
