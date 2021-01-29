package com.jjw.learnKorean.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jjw.learnKorean.component.fragment.HistoryFragment
import com.jjw.learnKorean.component.fragment.PlaylistFragment
import com.jjw.learnKorean.component.fragment.TutorialFragment

class MainAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa){

//    private val fragmentTitleList = mutableListOf("main","playlist","culture")

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0-> TutorialFragment()
            1 -> PlaylistFragment()
            else -> TutorialFragment()
        }
    }

}