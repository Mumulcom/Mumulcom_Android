package com.example.mumulcom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mumulcom.databinding.ActivityQuestionDetailBinding

class QuestionDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityQuestionDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val bigCategoryName = intent.getStringExtra("bigCategoryName")
        val questionIdx = intent.getLongExtra("questionIdx",0) // 받아온 질문 고유번호 -> api 호출시 넘김

        binding.categoryNameTv.text = bigCategoryName




    }// end of onCreate
}// end of class