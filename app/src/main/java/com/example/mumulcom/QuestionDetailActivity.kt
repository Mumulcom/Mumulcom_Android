package com.example.mumulcom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mumulcom.databinding.ActivityQuestionDetailBinding

class QuestionDetailActivity : AppCompatActivity() {

    private lateinit var bigCategoryName : String
    private var questionIdx : Long = 0 // default 값

    private lateinit var binding : ActivityQuestionDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        bigCategoryName = intent.getStringExtra("bigCategoryName")!!
        questionIdx = intent.getLongExtra("questionIdx",0) // 받아온 질문 고유번호 -> api 호출시 넘김

        binding.categoryNameTv.text = bigCategoryName

        binding.backIv.setOnClickListener {  // 뒤로 가기 버튼 클릭시
            finish()
        }




    }// end of onCreate
}// end of class