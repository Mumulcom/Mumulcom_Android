package com.example.mumulcom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mumulcom.databinding.ActivityReportBinding

// 신고하기 activity
class ReportActivity : AppCompatActivity() {


    private lateinit var binding:ActivityReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backIv.setOnClickListener {
            finish()
        }

        // 욕설
        binding.report1Iv.setOnClickListener {

        }
        // 홍보
        binding.report2Iv.setOnClickListener {

        }
        // 불건전 닉네임
        binding.report3Iv.setOnClickListener {

        }
        // 도배
        binding.report4Iv.setOnClickListener {

        }
        // 성희롱
        binding.report5Iv.setOnClickListener {

        }
        // 기타
        binding.report6Iv.setOnClickListener {

        }

    }// end of onCreate
}// end of class