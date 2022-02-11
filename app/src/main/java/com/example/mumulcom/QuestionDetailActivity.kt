package com.example.mumulcom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mumulcom.adapter.ImageViewPagerAdapter
import com.example.mumulcom.adapter.RepliesForQuestionAdapter
import com.example.mumulcom.data.*
import com.example.mumulcom.databinding.ActivityQuestionDetailBinding
import com.example.mumulcom.service.DetailCodingQuestionService
import com.example.mumulcom.service.DetailConceptQuestionService
import com.example.mumulcom.service.LikeQuestionService
import com.example.mumulcom.service.RepliesForQuestionService
import com.example.mumulcom.view.DetailCodingQuestionView
import com.example.mumulcom.view.DetailConceptQuestionView
import com.example.mumulcom.view.LikeQuestionView
import com.example.mumulcom.view.RepliesForQuestionView


// 질문 상세 페이지 (개념/코딩)
class QuestionDetailActivity : AppCompatActivity(), DetailCodingQuestionView,
    DetailConceptQuestionView, LikeQuestionView,
    RepliesForQuestionView {// end of class
    private lateinit var binding : ActivityQuestionDetailBinding
    private lateinit var bigCategoryName : String
    private var questionIdx : Long = 0 // default 값
    private var type : Int = 0
    private var isLiked = false // 질문에 대한 좋아요 변수

    private lateinit var repliesForQuestionAdapter: RepliesForQuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        bigCategoryName = intent.getStringExtra("bigCategoryName")!!
        questionIdx = intent.getLongExtra("questionIdx",0) // 받아온 질문 고유번호 -> api 호출시 넘김
        type = intent.getIntExtra("type",0) // 1: 코딩질문, 2: 개념질문
        binding.categoryNameTv.text = bigCategoryName
        Log.d("type확인 ",type.toString())

        when(type){
            1-> getDetailCodingQuestion() // 코딩 질문
            2-> getDetailConceptQuestion() // 개념질문
        }

        getRepliesForQuestion() // 질문에 대한 답변 받아오는 함수
        initRecyclerView()


        binding.backIv.setOnClickListener {  // 뒤로 가기 버튼 클릭시
            finish()
        }

        binding.refreshLayout.setOnRefreshListener {
            //  서버에서 데이터 reload
            getRepliesForQuestion() // 질문에 대한 답변 받아오는 함수
            initRecyclerView()

            binding.refreshLayout.isRefreshing = false
        }

        binding.clickLikeIv.setOnClickListener {  // 해당 질문에 좋아요를 눌렀을때

            isLiked = !isLiked  //

            setLikeQuestion()



            Log.d("lifecycle","QuestionDetailActivity onCreate")



        }

    }// end of onCreate

    override fun onStart() {
        super.onStart()

        Log.d("lifecycle","QuestionDetailActivity onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle","QuestionDetailActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle","QuestionDetailActivity onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle","QuestionDetailActivity onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle","QuestionDetailActivity onDestroy")

    }

    private fun setLikeQuestion(){
        if(isLiked){ // 좋아요를 했을때
            binding.clickLikeIv.setImageResource(R.drawable.ic_liked)

            // 서버호출
            setLikeForQuestion()

        }else{ // 좋아요를 취소했을때
            binding.clickLikeIv.setImageResource(R.drawable.ic_like)
            //  서버호출
            setLikeForQuestion()
        }
    }


    private fun setLikeForQuestion(){
        val likeQuestionService = LikeQuestionService()
        likeQuestionService.setLikeQuestionService(this)
        Log.d("qqq","setLikeForQuestion 호출")
        likeQuestionService.getLikeQuestion(getJwt(this), LikeSend(questionIdx, getUserIdx(this)))

    }



    private fun initRecyclerView(){
        // recyclerView <-> adapter 연결
        repliesForQuestionAdapter = RepliesForQuestionAdapter(this)

        binding.recyclerView.adapter = repliesForQuestionAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
    }


    private fun getRepliesForQuestion(){// 질문에 대한 답변 받아오는 함수
        val repliesForQuestionService = RepliesForQuestionService()
        repliesForQuestionService.setRepliesForQuestionService(this)
        repliesForQuestionService.getRepliesForQuestion(questionIdx, getUserIdx(this))
    }

    private fun getDetailConceptQuestion(){ // 개념질문 가져옴
        val detailConceptQuestionService = DetailConceptQuestionService()
        detailConceptQuestionService.setDetailConceptQuestionService(this)
        detailConceptQuestionService.getDetailConceptQuestion(questionIdx, getUserIdx(this))

    }

    private fun getDetailCodingQuestion(){ // 코딩질문 가져옴
        val detailCodingQuestionService = DetailCodingQuestionService()
        detailCodingQuestionService.setDetailCodingQuestionService(this)
        detailCodingQuestionService.getDetailConceptQuestion(questionIdx, getUserIdx(this))

    }




    // ----------- DetailConceptQuestionView implement : 개념 질문 -----------------

    override fun onGetDetailConceptQuestionsLoading() {
        Log.d("개념질문 상세페이지/API","로딩중...")
    }

    override fun onGetDetailConceptQuestionsSuccess(result: ArrayList<DetailConceptQuestion>) {
//        Log.d("size 확인111",result.size.toString())
//        Log.d("size 확인111",result[0].nickname)
        binding.nickNameTv.text = result[0].nickname // 닉네임
        binding.createdAtTv.text = result[0].createdAt // 작성날짜
        binding.questionIv.setImageResource(R.drawable.ic_concept_question_check_img) // 코딩 이미지로바꿈
        Glide.with(this).load(result[0].profileImgUrl).into(binding.profileIv) // 프로필 이미지
        binding.titleTv.text = result[0].title // 제목
        binding.bigCategoryTv.text = "#"+result[0].bigCategoryName // 상위 카테고리
        if(result[0].smallCategoryName!=null){
            binding.smallCategoryTv.text = "#"+result[0].smallCategoryName // 하위 카테고리
        }
        //  이미지 있으면 그 수만큼 viewpager 어댑터에 넘기고 없으면 이미지 보여주는 부분 gone 처리
        if(result[0].questionImgUrls.size == 0){
            binding.pictureLinearLayout.visibility = View.GONE
            Log.d("이미지test","사진 viewpager gone")
        }else{
            val imageViewPagerAdapter = ImageViewPagerAdapter(this)
            imageViewPagerAdapter.addQuestions(result[0].questionImgUrls!!)
            binding.viewPager.adapter = imageViewPagerAdapter
            binding.indicator.setViewPager(binding.viewPager)


            Log.d("이미지test","어댑터로 넘김")

        }

        binding.currentErrorTv.text = result[0].content // 질문 내용
        binding.codingSkillConstraintLayout.visibility = View.GONE

        if(result[0].isLiked =="Y"){
            isLiked = true
            binding.clickLikeIv.setImageResource(R.drawable.ic_liked)
        }

        Log.d("개념질문 idx",result[0].questionIdx.toString())

    }


    override fun onGetDetailConceptQuestionsFailure(code: Int, message: String) {
        when(code){
            400-> Log.d("개념질문 상세페이지/API",message)
        }
    }






    // ----------- DetailCodingQuestionView implement : 코딩 질문 -----------------

    override fun onGetDetailCodingQuestionsLoading() {
        Log.d("코딩질문 상세페이지/API","로딩중...")
    }

    override fun onGetDetailCodingQuestionsSuccess(result: ArrayList<DetailCodingQuestion>) {
//        Log.d("size 확인222",result.size.toString())
//        Log.d("size 확인222",result[0].nickname)
        binding.nickNameTv.text = result[0].nickname // 닉네임
        binding.createdAtTv.text = result[0].createdAt // 작성날짜
        Glide.with(this).load(result[0].profileImgUrl).into(binding.profileIv) // 프로필 이미지
        binding.titleTv.text = result[0].title // 제목
        binding.bigCategoryTv.text = "#"+result[0].bigCategoryName // 상위 카테고리
        if(result[0].smallCategoryName!=null){
            binding.smallCategoryTv.text = "#"+result[0].smallCategoryName // 하위 카테고리
        }

        //  이미지 있으면 그 수만큼 viewpager 어댑터에 넘기고 없으면 이미지 보여주는 부분 gone 처리
        Log.d("이미지test",result[0].questionImgUrls.toString())
//        Log.d("이미지test",result[0].questionImgUrls!!.isEmpty().toString())
//        Log.d("이미지test--",result[0].questionImgUrls[0].toString())

        if(result[0].questionImgUrls.size == 0){
            binding.pictureLinearLayout.visibility = View.GONE
            Log.d("이미지test","사진 viewpager gone")
        }else{
            val imageViewPagerAdapter = ImageViewPagerAdapter(this)
            imageViewPagerAdapter.addQuestions(result[0].questionImgUrls!!)
            binding.viewPager.adapter = imageViewPagerAdapter
            binding.indicator.setViewPager(binding.viewPager)


            Log.d("이미지test","어댑터로 넘김")

        }
        binding.currentErrorTv.text = result[0].currentError // 질문 내용


        if(result[0].myCodingSkill == null){ // 내 코딩 실력
            binding.codingSkillConstraintLayout.visibility = View.GONE
        }else{
            binding.myCodingSkillTv.text = result[0].myCodingSkill
        }

        Log.d("코딩질문 idx",result[0].questionIdx.toString())

        if(result[0].isLiked =="Y"){
            isLiked = true
            binding.clickLikeIv.setImageResource(R.drawable.ic_liked)
        }



    }

    override fun onGetDetailCodingQuestionsFailure(code: Int, message: String) {
        when(code){
            400-> Log.d("코딩질문 상세페이지/API",message)
        }
    }






    // ----------- RepliesForQuestionView implement : 질문에 대한 답변들 -----------------

    override fun onGetRepliesLoading() {
        Log.d("질문에 대한 답변/API","로딩중...")
    }

    override fun onGetRepliesSuccess(result: ArrayList<Reply>) {
        repliesForQuestionAdapter.addQuestions(result)

    }

    override fun onGetRepliesFailure(code: Int, message: String) {
        when(code){
            400-> Log.d("질문에 대한 답변/API",message)
        }
    }


    // -------------- LikeQuestionView implement : 질문에 대한 좋아요 ---------------

    override fun onGetLikeQuestionLoading() {
        Log.d("질문에 대한 좋아요/API","로딩중...")
    }

    override fun onGetLikeQuestionSuccess(result: Like) {
        Log.d("likeTest","질문을 만든 유저 id : "+result.noticeTargetUserIdx) // 해당 질문을 작성한 유저 id
        Log.d("likeTest","좋아요 내용 :  "+result.noticeContent)  // 000 글을 좋아요 했습니다./취소 했습니다.

    }

    override fun onGetLikeQuestionFailure(code: Int, message: String) {
        when(code){
            400-> Log.d("질문에 대한 좋아요/API",message)
        }
    }


}