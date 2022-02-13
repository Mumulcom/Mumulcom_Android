package com.example.mumulcom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mumulcom.databinding.ActivityQuestioncategoryBinding


class QuestionCategoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuestioncategoryBinding

    private var codingQuestionCheck : Boolean = false // default 값
    private var conceptQuestionCheck : Boolean = false // default 값
    private var isSelect: Boolean=false // default 값

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestioncategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSelectCompletion()//선택완료버튼
        initView()  // view 초기화
        initCodingOrConceptQuestionButton() // 코딩 질문 & 개념 질문 버튼 초기화

    }

    private fun initView(){

        if (codingQuestionCheck==false&&conceptQuestionCheck==false){
            isSelect=false
            setSelectCompletion()
            binding.questioncategorySelectCompletionIb.setOnClickListener {
                Toast.makeText(this@QuestionCategoryActivity, "질문 유형을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // 코딩 질문 버튼
        binding.questioncategoryCodingQuestionIb.setOnClickListener {
            codingQuestionCheck = true
            conceptQuestionCheck = false
            initCodingOrConceptQuestionButton()
            isSelect=true
            setSelectCompletion()
            binding.questioncategorySelectCompletionIb.setOnClickListener {
                startActivity(Intent(this, CheckCodingQuestionActivity::class.java))
            }
        }


        // 개념 질문 버튼
        binding.questioncategoryConceptQuestionIb.setOnClickListener {
            conceptQuestionCheck= true
            codingQuestionCheck = false
            isSelect=true
            initCodingOrConceptQuestionButton()
            setSelectCompletion()
            binding.questioncategorySelectCompletionIb.setOnClickListener {
                startActivity(Intent(this, CheckConceptQuestionActivity::class.java))
            }
        }

    }

    fun setSelectCompletion(){
        if(isSelect){
            binding.questioncategorySelectCompletionIb.setImageResource(R.drawable.ic_select_complete)
//            binding.questioncategorySelectCompletionIb.setOnClickListener {
//                startActivity(Intent(this, CheckCodingQuestionActivity::class.java))
//            }
        }else{
            binding.questioncategorySelectCompletionIb.setImageResource(R.drawable.ic_bf_selectcompletion)
        }
    }




    fun initCodingOrConceptQuestionButton(){
        if(codingQuestionCheck){
            binding.questioncategoryCodingQuestionIb.setImageResource(R.drawable.coding_question_check_img)
            binding.questioncategoryCodingQuestionIb.isEnabled = false // 이미 선택되었으면 선택 못함.
            // 코딩 질문만 보여줌
        }else{
            binding.questioncategoryCodingQuestionIb.setImageResource(R.drawable.coding_question_img)
            binding.questioncategoryCodingQuestionIb.isEnabled = true
        }

        if(conceptQuestionCheck){
            binding.questioncategoryConceptQuestionIb.setImageResource(R.drawable.ic_concept_question_check_img)
            binding.questioncategoryConceptQuestionIb.isEnabled = false // 이미 선택되었으면 선택 못함.
            // 개념 질문만 보여줌

        }else{
            binding.questioncategoryConceptQuestionIb.setImageResource(R.drawable.ic_concept_question_img)
            binding.questioncategoryConceptQuestionIb.isEnabled = true
        }
    }
}