package com.example.mumulcom

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mumulcom.databinding.ItemAlarmBinding
import com.example.mumulcom.databinding.ItemAlarmHeaderBinding
import com.example.mumulcom.databinding.QuestionListItemBinding

class AlarmAdapter(var context: Context?)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = ArrayList<AlarmListItem>()

    private val alarmList = ArrayList<Alarm>()

    // 알람을 클릭하면 -> 해당 질문으로 가기
    interface AlarmClickListener{
        fun onItemClick(alarm: Alarm)
    }

    // 리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var alarmClickListener : AlarmAdapter.AlarmClickListener

    fun setAlarmClickListener(alarmClickListener: AlarmAdapter.AlarmClickListener){
        this.alarmClickListener = alarmClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // val binding: ItemAlarmBinding = ItemAlarmBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        // return AlarmViewHolder(binding)
        return when (viewType) {
            AlarmListItem.TYPE_DATE -> AlarmDateViewHolder(ItemAlarmHeaderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            else -> AlarmViewHolder(ItemAlarmBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        /* holder.bind(alarmList[position])
        //recyclerView 의 각 아이템을 클릭할때
        holder.itemView.setOnClickListener {
            alarmClickListener.onItemClick(alarmList[position])
        }*/

        when (holder.itemViewType) {
            AlarmListItem.TYPE_DATE -> (holder as AlarmDateViewHolder).bind(
                items[position] as AlarmDateItem
            )
            AlarmListItem.TYPE_GENERAL -> (holder as AlarmViewHolder).bind(
                items[position] as AlarmGeneralItem
            )

        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAlarm(alarm:ArrayList<Alarm>){
        this.alarmList.clear()
        this.alarmList.addAll(alarm)

        notifyDataSetChanged()
    }

    inner class AlarmDateViewHolder(val binding: ItemAlarmHeaderBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(alarmDate: AlarmDateItem) {
            binding.itemAlarmHeader.text = alarmDate.diffTime
        }
    }

    inner class AlarmViewHolder(val binding: ItemAlarmBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(alarmItem: AlarmGeneralItem) {
            context?.let { Glide.with(it).load(alarmItem.profileImgUrl).into(binding.itemAlarmProfileIv) }
            binding.itemAlarmContentTv.text = alarmItem.content
        }
    }
}