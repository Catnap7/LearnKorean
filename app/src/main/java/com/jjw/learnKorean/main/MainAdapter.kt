package com.jjw.learnKorean.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.view.ViewGroup

class MainAdapter(fm: FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm){

    private val fragmentTitleList = mutableListOf("main","playlist","culture")

    override fun getItem(p0: Int): Fragment {

        return when(p0){

            0 ->  TutorialFragment()
            1 ->  PlaylistFragment()
            2 ->  HistoryFragment()
            else -> TutorialFragment()
        }
    }

    override fun getCount(): Int = 3

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }

}