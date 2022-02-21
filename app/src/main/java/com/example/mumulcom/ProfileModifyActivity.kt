package com.example.mumulcom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mumulcom.databinding.ActivityProfileModifyBinding

class ProfileModifyActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로 가기 버튼 클릭
        binding.profileModifyBackIv.setOnClickListener {
            onBackPressed()
        }
    }
}
