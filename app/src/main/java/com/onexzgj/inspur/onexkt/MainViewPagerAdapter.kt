package com.onexzgj.inspur.onexkt

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * des：
 * author：onexzgj
 * time：2020-03-05
 */

class MainViewPagerAdapter(
    var activity: FragmentActivity,
    var fragments: List<Fragment>
) : FragmentStateAdapter(activity) {


    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}