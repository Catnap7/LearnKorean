package com.jjw.learnKorean.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import android.view.ViewGroup

class MainAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm){

    private val fragmentTitleList = mutableListOf("tutorial","playlist","history")

    override fun getItem(p0: Int): androidx.fragment.app.Fragment? {

        return when(p0){

            0 ->  TutorialFragment()
            1 ->  PlaylistFragment()
            2 ->  HistoryFragment()
            else -> null
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