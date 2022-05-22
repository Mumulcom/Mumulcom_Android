package com.example.mumulcom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mumulcom.databinding.ActivityReportContentBinding

class ReportContentActivity : AppCompatActivity() {

    lateinit var binding:ActivityReportContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityReportContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backIv.setOnClickListener {
            finish()
        }

        binding.sendReportBtn.setOnClickListener {
            // todo api 호출
        }
    }
}