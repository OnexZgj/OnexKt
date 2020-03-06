package com.onexzgj.inspur.onexkt.ui.project

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.onexzgj.inspur.onexkt.model.FragmentItem

/**
 * des：
 * author：onexzgj
 * time：2020-03-06
 */
class ProjectPagerAdapter(
    activity: AppCompatActivity,
    fragmentManager: FragmentManager,
    var datas: List<FragmentItem>
) :
    FragmentStateAdapter(fragmentManager, activity.lifecycle) {
    override fun getItemCount(): Int {
        return datas.size
    }

    override fun createFragment(position: Int): Fragment {
        return datas[position].fragment

    }

}