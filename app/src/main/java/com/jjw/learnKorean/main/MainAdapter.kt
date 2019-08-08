package com.jjw.learnKorean.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup

class MainAdapter(fm:FragmentManager) :FragmentStatePagerAdapter(fm){

    private val fragmentTitleList = mutableListOf("tutorial","playlist","history")

    override fun getItem(p0: Int): Fragment? {

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