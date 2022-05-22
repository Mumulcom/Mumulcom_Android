package com.example.mumulcom

import android.content.Intent
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
            val intent = Intent(this,ReportContentActivity::class.java)
            intent.putExtra("reportTypeIdx",1)
            startActivity(intent)

        }
        // 홍보
        binding.report2Iv.setOnClickListener {
            val intent = Intent(this,ReportContentActivity::class.java)
            intent.putExtra("reportTypeIdx",2)
            startActivity(intent)
        }
        // 불건전 닉네임
        binding.report3Iv.setOnClickListener {
            val intent = Intent(this,ReportContentActivity::class.java)
            intent.putExtra("reportTypeIdx",3)
            startActivity(intent)
        }
        // 도배
        binding.report4Iv.setOnClickListener {
            val intent = Intent(this,ReportContentActivity::class.java)
            intent.putExtra("reportTypeIdx",4)
            startActivity(intent)
        }
        // 성희롱
        binding.report5Iv.setOnClickListener {
            val intent = Intent(this,ReportContentActivity::class.java)
            intent.putExtra("reportTypeIdx",5)
            startActivity(intent)
        }
        // 기타
        binding.report6Iv.setOnClickListener {
            val intent = Intent(this,ReportContentActivity::class.java)
            intent.putExtra("reportTypeIdx",6)
            startActivity(intent)
        }

    }// end of onCreate
}// end of class