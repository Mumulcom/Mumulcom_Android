package com.example.mumulcom.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mumulcom.R
import com.example.mumulcom.data.Comment
import com.example.mumulcom.data.Like
import com.example.mumulcom.data.LikeReplySend
import com.example.mumulcom.data.Reply
import com.example.mumulcom.databinding.QuestionAnswerItemBinding
import com.example.mumulcom.getJwt
import com.example.mumulcom.getUserIdx
import com.example.mumulcom.service.CommentsForReplyService
import com.example.mumulcom.service.LikeReplyService
import com.example.mumulcom.view.CommentsForReplyView
import com.example.mumulcom.view.LikeReplyView

class RepliesForQuestionAdapter(val context: Context):RecyclerView.Adapter<RepliesForQuestionAdapter.ViewHolder>(),LikeReplyView,CommentsForReplyView {


    private val replyList = ArrayList<Reply>()
    private var isAdopted : String ="N" // 채택하기
    private var isLike : Boolean = false // 좋아요
    private var isCommentClick : Boolean = false // comment 이미지를 클릭했는지 여부 확인
    private lateinit var imageViewPagerAdapter: ImageViewPagerAdapter
    private  var replyIdx : Long = -1
    var likeNumber : Int = 0
    private lateinit var commentsForReplyAdapter: CommentsForReplyAdapter




    // 클릭 인터페이스 정의
    interface RepliesItemClickListener{
        fun onRemoveAnswerButton(isClicked:Boolean)
    }

    // 리스너 객체를 전달받는 함수랑 리스너 객체 저장 변수
    private lateinit var repliesItemClickListener : RepliesItemClickListener

    fun setRepliesClickListener(repliesItemClickListener: RepliesItemClickListener){
        this.repliesItemClickListener = repliesItemClickListener
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: QuestionAnswerItemBinding =  QuestionAnswerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        binding.commentTv.setOnClickListener {
            Log.d("umc","게시를 누름.")
        }
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(replyList[position])
        holder.binding.viewPager.setOnClickListener {
            // todo 이미지 확대
        }
        // 좋아요 처리
        holder.binding.itemLikeIv.setOnClickListener {
            isLike = !isLike
            if(isLike){
                holder.binding.itemLikeIv.setImageResource(R.drawable.ic_liked)
                setLikeReply() // 답변에 대한 좋아요 처리
            //    likeNumber++
            }else{
                holder.binding.itemLikeIv.setImageResource(R.drawable.ic_like)
                setLikeReply() // 답변에 대한 좋아요 처리
            //    likeNumber--
            }
        }
        // 채택하기 처리
        holder.binding.selectAnswerTv.setOnClickListener {
            holder.binding.selectAnswerTv.visibility = View.GONE
            holder.binding.selectAnswerIv.visibility = View.VISIBLE
        }
        holder.binding.selectAnswerIv.setOnClickListener {
            holder.binding.selectAnswerIv.visibility = View.GONE
            holder.binding.selectAnswerTv.visibility = View.VISIBLE
        }

        // 댓글 처리
        holder.binding.commentIv.setOnClickListener {
            isCommentClick=!isCommentClick
            if(isCommentClick){
                holder.binding.commentIv.setImageResource(R.drawable.ic_message_select)
                holder.binding.itemCommentTv.setTextColor(Color.parseColor("#F7B77C"))
                holder.binding.commentLinearLayout.visibility = View.VISIBLE // 댓글창 염
                // recyclerView adapter 연결
                // todo api 연결
                getCommentsForReply() // 댓글 가져오는 api 호출
                commentsForReplyAdapter = CommentsForReplyAdapter(context)
                holder.binding.commentRecyclerView.adapter = commentsForReplyAdapter
                holder.binding.commentRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)




            }else{
                holder.binding.commentIv.setImageResource(R.drawable.ic_message)
                holder.binding.itemCommentTv.setTextColor(Color.parseColor("#000000"))
                holder.binding.commentLinearLayout.visibility = View.GONE // 댓글창 닫음.
            }
            //  답변하기 버튼 gone or visible 변경 (QuestionDetailActivity)
            repliesItemClickListener.onRemoveAnswerButton(isCommentClick)


        }





    }// end of onBindViewHolder


    private fun getCommentsForReply(){
        val commentsForReplyService = CommentsForReplyService()
        commentsForReplyService.setCommentsForReplyView(this)
        Log.d("replyIdx:",replyIdx.toString())
        commentsForReplyService.getCommentsForReply(46)
      //  commentsForReplyService.getCommentsForReply(replyIdx)
    }

    override fun getItemCount(): Int {
        return replyList.size
    }




    @SuppressLint("NotifyDataSetChanged")
    fun addQuestions(replies:ArrayList<Reply> ){
        this.replyList.clear()
        this.replyList.addAll(replies)

        notifyDataSetChanged()
    }



    inner class ViewHolder(val binding:QuestionAnswerItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(reply: Reply){
            Glide.with(context).load(reply.profileImgUrl).into(binding.profileIv) // 프로필 이미지
            binding.nickNameTv.text = reply.nickname // 닉네임
            binding.createdAtTv.text = reply.createdAt // 작성 날짜

            likeNumber = Integer.parseInt(binding.itemLikeTv.text.toString())


            if(reply.replyUrl==null){
//                binding.replyUrl.visibility = View.GONE
                binding.replyUrl.text = "참고 링크 : 없음."// 참고 링크
            }else{
                binding.replyUrl.text = "참고 링크 : "+reply.replyUrl // 참고 링크
            }

            binding.contentTv.text = reply.content // 답변 내용
            binding.contentTv.text = reply.reReplyCount.toString() // 대댓글수
            binding.itemLikeTv.text = reply.likeCount.toString() // 좋아요 수

            // 답변 이미지 viewpager 연결
            imageViewPagerAdapter = ImageViewPagerAdapter(context)
            if(reply.replyImgUrl.size==0){
                binding.itemL13.visibility = View.GONE
            }else{
                imageViewPagerAdapter.addQuestions(reply.replyImgUrl)
                binding.viewPager.adapter = imageViewPagerAdapter
                binding.indicator.setViewPager(binding.viewPager)
            }

            if(reply.isLiked=="Y"){
                isLike= true
                binding.itemLikeIv.setImageResource(R.drawable.ic_liked)
            }

            replyIdx =reply.replyIdx // 답변에 대한 고유 번호 저장. -> 서버에 넘겨서 댓글 받아옴.

//            if(isAdopted=="Y"){ // 이미 답변이 하나라도 채택이 됬다면 채택버튼은 사라짐.
//                binding.selectAnswerIv.visibility = View.GONE
//                binding.selectAnswerTv.visibility = View.GONE
//            }


        }// end of bind()

    }


    private fun setLikeReply(){
        val likeReplyService = LikeReplyService()
        likeReplyService.setLikeReplyView(this)
        likeReplyService.getLikeReply(getJwt(context), LikeReplySend(replyIdx,getUserIdx(context)))
    }








    // --------------- LikeReplyView implement : 답변에 대한 좋아요 ----------------------
    override fun onGetLikeReplyLoading() {
        Log.d("답변에 대한 좋아요/API","로딩중...")
    }

    override fun onGetLikeReplySuccess(result: Like) {
        Log.d("likeTest","질문을 만든 유저 id : "+result.noticeTargetUserIdx) // 해당 질문을 작성한 유저 id
        Log.d("likeTest","좋아요 내용 :  "+result.noticeContent)  // 000 글을 좋아요 했습니다./취소 했습니다.
    }

    override fun onGetLikeReplyFailure(code: Int, message: String) {
        when(code){
            400-> Log.d("개념질문 상세페이지/API",message)
        }
    }



    // ---------------  CommentsForReplyView  implement : 답변에 대한 댓글 ----------------

    override fun onGetCommentsLoading() {
        Log.d("답변에 대한 댓글 가져오기/API","로딩중...")
    }

    override fun onGetCommentsSuccess(result: ArrayList<Comment>) {
        Log.d("답변에 대한 댓글 가져오기/API","성공")
        commentsForReplyAdapter.addComments(result)


    }

    override fun onGetCommentsFailure(code: Int, message: String) {
        when(code){
            400-> Log.d("개념질문 상세페이지/API",message)
        }
    }




}