package com.example.mumulcom

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity

import android.view.View
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.mumulcom.databinding.ActivityCheckcodingquestionBinding
import kotlin.collections.ArrayList
import android.util.Log
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView


//import android.R


class CheckCodingQuestionActivity:AppCompatActivity() {

    lateinit var binding: ActivityCheckcodingquestionBinding

    private lateinit var getResultText: ActivityResultLauncher<Intent>

    private var albumDatas=ArrayList<Album>()//뷰페이저

    private var bigCategory: String? = null    // 선택한 상위 카테고리
    private var smallCategory: String? = null  // 선택한 하위 카테고리

    // 스피너 어댑터
    private lateinit var bigCategoryAdapter: ArrayAdapter<String>
    private lateinit var smallCategoryAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckcodingquestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 카테고리 초기화
        setupBigCategorySpinner()
        setupBigCategorySpinnerHandler()

        binding.checkcodingquestionBackIv.setOnClickListener {
            startActivity(Intent(this, CodingCameraShootingActivity::class.java))
        }

        binding.checkcodingquestionBackIv.setOnClickListener {
            startActivity(Intent(this, QuestionCategoryActivity::class.java))
        }

        binding.checkcodingquestionPlusIv.setOnClickListener {
            startActivity(Intent(this, CodingCameraShootingActivity::class.java))
        }

        binding.checkcodingquestionQuestionIv.setOnClickListener {
            required()
        }


//        //뷰페이저 데이터
//        albumDatas.apply {
//            add(Album(Uri.parse(intent.getStringExtra("uri"))))
//        }


        val ViewPagerAdapter= ViewPagerAdapter(albumDatas)
        binding.checkcodingquestionVp.adapter=ViewPagerAdapter// 뷰페이저 어댑터 생성
        binding.checkcodingquestionVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
        binding.checkcodingIndicator.setViewPager(binding.checkcodingquestionVp)
        binding.checkcodingIndicator.createIndicators(5,0);
    }

    private fun required() {
        /*if (selectDatas.isEmpty()||selectBottomDatas.isEmpty()){
            Toast.makeText(this, "카테고리를 선택해주세요.", Toast.LENGTH_SHORT).show()

            return
        }*/
        if (binding.checkcodingquestionTitleTextEt.text.isEmpty()) {

            Toast.makeText(this, "제목을 작성해주세요.", Toast.LENGTH_SHORT).show()

            return
        }
        if (binding.checkcodingquestionStopPartTextEt.text.isEmpty()) {

            Toast.makeText(this, "현재 막힌 부분을 작성해주세요.", Toast.LENGTH_SHORT).show()

            return
        }
        binding.checkcodingquestionQuestionIv.setImageResource(R.drawable.ic_click_question)
    }


    /********************* 스피너 ********************/
    // dp 값을 px 값으로 변환해주는 함수
    private fun dipToPixels(dipValue: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dipValue,
            resources.displayMetrics
        )
    }

    // big category spinner에 arrayadapter 연결
    private fun setupBigCategorySpinner() {
        // 상위 카테고리들 (앱, 웹, 서버, 프로그래밍 언어, 기타)
        val bigCategoryArray = resources.getStringArray(R.array.big_category)
        bigCategoryAdapter = object : ArrayAdapter<String>(this, R.layout.item_big_category){
            @SuppressLint("CutPasteId")
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val v = super.getView(position, convertView, parent)
                if(position == count) {
                    // 마지막 포지션의 textView를 힌트용으로 사용
                    (v.findViewById<View>(R.id.item_big_category_tv) as TextView).text = ""
                    // 아이템의 마지막 값을 불러와 hint로 추가함
                    (v.findViewById<View>(R.id.item_big_category_tv) as TextView).hint = getItem(count)
                }
                return v
            }
            override fun getCount(): Int {
                // 마지막 아이템은 hint용이기 때문에 1을 빼줌
                return super.getCount() - 1
            }
        }
        // 아이템 추가
        bigCategoryAdapter.addAll(bigCategoryArray.toMutableList())
        // hint로 사용할 문구를 마지막 아이템에 추가
        bigCategoryAdapter.add("상위 선택")
        // 어댑터 연결
        binding.checkcodingquestionBigCategorySp.adapter = bigCategoryAdapter
        // 스피너 초기값을 마지막 아이템으로 설정
        binding.checkcodingquestionBigCategorySp.setSelection(bigCategoryAdapter.count)

        // droplist를 스피너와 간격을 두고 나오게 함 -> 아이템 크기 = 125px
        binding.checkcodingquestionBigCategorySp.dropDownVerticalOffset = dipToPixels(45f).toInt()
    }

    // 스피너 클릭 이벤트 핸들러
    private fun setupBigCategorySpinnerHandler() {
        binding.checkcodingquestionBigCategorySp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.checkcodingquestionBigCategorySp.setBackgroundResource(R.drawable.bg_category_outline)
                binding.checkcodingquestionSmallCategorySp.isEnabled = true

                if (binding.checkcodingquestionBigCategorySp.getItemAtPosition(position).equals("앱")) {
                    selectedCategory(position)
                    binding.checkcodingquestionSmallCategorySp.visibility = View.VISIBLE
                    // 앱 하위 카테고리 (안드로이드, iOS)
                    val smallCategoryAppArray = resources.getStringArray(R.array.small_category_app)
                    setupSmallCategorySpinner(smallCategoryAppArray)
                    setupSmallCategorySpinnerHandler()
                }

                else if (binding.checkcodingquestionBigCategorySp.getItemAtPosition(position).equals("웹")) {
                    selectedCategory(position)
                    binding.checkcodingquestionSmallCategorySp.visibility = View.VISIBLE
                    // 웹 하위 카테고리 (HTML, CSS, React)
                    val smallCategoryWebArray = resources.getStringArray(R.array.small_category_web)
                    setupSmallCategorySpinner(smallCategoryWebArray)
                    setupSmallCategorySpinnerHandler()
                }

                else if (binding.checkcodingquestionBigCategorySp.getItemAtPosition(position).equals("서버")) {
                    selectedCategory(position)
                    binding.checkcodingquestionSmallCategorySp.visibility = View.VISIBLE
                    // 서버 하위 카테고리 (Node.js, Spring)
                    val smallCategoryServerArray = resources.getStringArray(R.array.small_category_server)
                    setupSmallCategorySpinner(smallCategoryServerArray)
                    setupSmallCategorySpinnerHandler()
                }

                else if (binding.checkcodingquestionBigCategorySp.getItemAtPosition(position).equals("프로그래밍 언어")) {
                    selectedCategory(position)
                    binding.checkcodingquestionSmallCategorySp.visibility = View.VISIBLE
                    // 프로그래밍 언어 하위 카테고리 (C, C++, JavaScript, Java, Python)
                    val smallCategoryProgramingArray = resources.getStringArray(R.array.small_category_programing)
                    setupSmallCategorySpinner(smallCategoryProgramingArray)
                    setupSmallCategorySpinnerHandler()
                }

                else if (binding.checkcodingquestionBigCategorySp.getItemAtPosition(position).equals("기타")) {
                    selectedCategory(position)
                    // 기타 선택 시 하위 선택 스피너 사라짐
                    binding.checkcodingquestionSmallCategorySp.visibility = View.GONE
                    // 하위 카테고리에 null 값
                    smallCategory = null
                }

                else if (binding.checkcodingquestionBigCategorySp.getItemAtPosition(position).equals("상위 선택")) {
                    bigCategory = null
                    Log.i(ContentValues.TAG, "상위 카테고리 확인: $bigCategory")
                    binding.checkcodingquestionSmallCategorySp.visibility = View.VISIBLE
                    // 하위 선택 뜨기
                    val smallCategoryArray = resources.getStringArray(R.array.small_category)
                    setupSmallCategorySpinner(smallCategoryArray)
                    setupSmallCategorySpinnerHandler()
                    // 하위 카테고리 스피너 사용 불가
                    binding.checkcodingquestionSmallCategorySp.isEnabled = false
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private fun selectedCategory(position: Int) {
        // 상위 선택하면 배경 변경
        binding.checkcodingquestionBigCategorySp.setBackgroundResource(R.drawable.bg_category_selected)
        // bigCategory 변수에 상위 카테고리 저장하기
        bigCategory = binding.checkcodingquestionBigCategorySp.getItemAtPosition(position).toString()
        Log.i(ContentValues.TAG, "상위 카테고리 확인: $bigCategory")
    }



    // big category spinner에 arrayadapter 연결
    private fun setupSmallCategorySpinner(array: Array<String>) {
        smallCategoryAdapter = object : ArrayAdapter<String>(this, R.layout.item_small_category){
            @SuppressLint("CutPasteId")
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val v = super.getView(position, convertView, parent)
                if(position == count) {
                    // 마지막 포지션의 textView를 힌트용으로 사용
                    (v.findViewById<View>(R.id.item_small_category_tv) as TextView).text = ""
                    // 아이템의 마지막 값을 불러와 hint로 추가함
                    (v.findViewById<View>(R.id.item_small_category_tv) as TextView).hint = getItem(count)
                }
                return v
            }
            override fun getCount(): Int {
                // 마지막 아이템은 hint용이기 때문에 1을 빼줌
                return super.getCount() - 1
            }
        }
        // 아이템 추가
        smallCategoryAdapter.addAll(array.toMutableList())
        // hint로 사용할 문구를 마지막 아이템에 추가
        smallCategoryAdapter.add("하위 선택")
        // 어댑터 연결
        binding.checkcodingquestionSmallCategorySp.adapter = smallCategoryAdapter
        // 스피너 초기값을 마지막 아이템으로 설정
        binding.checkcodingquestionSmallCategorySp.setSelection(smallCategoryAdapter.count)

        // droplist를 스피너와 간격을 두고 나오게 함 -> 아이템 크기 = 125px
        binding.checkcodingquestionSmallCategorySp.dropDownVerticalOffset = dipToPixels(45f).toInt()
    }

    // 스피너 클릭 이벤트 핸들러
    private fun setupSmallCategorySpinnerHandler() {
        binding.checkcodingquestionSmallCategorySp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.checkcodingquestionSmallCategorySp.setBackgroundResource(R.drawable.bg_category_outline)
                if (!binding.checkcodingquestionSmallCategorySp.getItemAtPosition(position).equals("하위 선택")) {
                    // 하위 카테고리 선택하면 배경 변경
                    binding.checkcodingquestionSmallCategorySp.setBackgroundResource(R.drawable.bg_category_selected)
                    // SmallCategory 변수에 하위 카테고리 저장하기
                    smallCategory = binding.checkcodingquestionSmallCategorySp.getItemAtPosition(position).toString()
                    Log.i(ContentValues.TAG, "하위 카테고리 확인: $smallCategory")
                } else {
                    smallCategory = null
                    Log.i(ContentValues.TAG, "하위 카테고리 확인: $smallCategory")
                    // Toast.makeText(context, "상위 카테고리를 선택해주세요!", Toast.LENGTH_SHORT).show()
                }

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

}