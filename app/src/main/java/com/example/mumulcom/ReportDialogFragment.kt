package com.example.mumulcom

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mumulcom.databinding.FragmentBottomReportSheetBinding
import com.example.mumulcom.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReportDialogFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentBottomReportSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomReportSheetBinding.inflate(inflater, container, false)

        binding.goToReportPage.setOnClickListener {
            val intent= Intent(requireContext(),ReportActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    companion object {
        const val TAG = "BottomReportSheetFragment"
    }
}