package com.jjw.learnKorean.korean

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjw.learnKorean.R
import com.jjw.learnKorean.databinding.ActivityMainBinding
import com.jjw.learnKorean.databinding.FragmentKoreanMainBinding
import kotlinx.android.synthetic.main.fragment_korean_main.view.*

class KoreanFragment : androidx.fragment.app.Fragment() {

    private var _binding: FragmentKoreanMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentKoreanMainBinding.inflate(inflater, container, false)
        val view = binding.root

        val koreanContentsList = arrayListOf("practical expression","a Korean proverb","an expression on the weather","an expression of taste","express used in restaurants")

        binding.rvKoreanContents.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvKoreanContents.adapter = KoreanListAdapter(requireActivity(),koreanContentsList)

        return view

    }

    inner class ViewModel{

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
