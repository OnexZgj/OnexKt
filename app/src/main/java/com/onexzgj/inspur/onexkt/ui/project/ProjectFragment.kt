package com.onexzgj.inspur.onexkt.ui.project

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.onexzgj.inspur.onexkt.MainViewPagerAdapter
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.model.ProjectTab
import com.onexzgj.inspur.onexkt.mvp.BaseMvpFragment
import com.onexzgj.inspur.onexkt.ui.project.detail.ProjectPageFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.fragment_project.*

class ProjectFragment : BaseMvpFragment<ProjectContract.View, ProjectPresentImp>(),
    ProjectContract.View {


    private lateinit var mAdapter: ProjectPagerAdapter


    private var fragments: MutableList<Fragment>? = null
    private lateinit var tabs: List<ProjectTab>

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {
//        vp_fp_viewpager.adapter = ProjectPagerAdapter(childFragmentManager,)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun createPresent(): ProjectPresentImp {
        return ProjectPresentImp()
    }

    override fun showTabs(datas: List<ProjectTab>?) {
        showInfo(" " + datas?.size)

        if (datas != null) {
            tabs = datas
        }

        val fragments = datas?.let { getFragmentItems(it) }

        mAdapter = fragments?.let { ProjectPagerAdapter(mActivity, it) }!!
        vp_fp_viewpager.adapter = mAdapter

        var mediator =
            TabLayoutMediator(tl_fp_tablayout, vp_fp_viewpager, true,
                TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                    tab.setCustomView(
                        makeTabView(position)
                    )
                })
        mediator.attach()


        vp_fp_viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val tabCount: Int = tl_fp_tablayout.getTabCount()
                for (i in 0 until tabCount) {
                    val tab: TabLayout.Tab? = tl_fp_tablayout.getTabAt(i)
                    val customView = tab?.customView as TextView?
                    if (tab?.position == position) {
                        customView!!.textSize = 16f
                        customView.typeface = Typeface.DEFAULT_BOLD
                    } else {
                        customView!!.textSize = 12f
                        customView.typeface = Typeface.DEFAULT
                    }
                }
            }
        })


    }


    fun makeTabView(position: Int): View? {
        val tabView = TextView(mContext)
        val states = arrayOfNulls<IntArray>(2)
        states[0] = intArrayOf(android.R.attr.state_selected)
        states[1] = intArrayOf()
        val colors = intArrayOf(
            Color.parseColor("#ED7282"),
            Color.parseColor("#666666")
        )
        val stateList = ColorStateList(states, colors)
        tabView.setTextColor(stateList)
        tabView.text = tabs[position].name
        tabView.textSize = 14f
        return tabView
    }

    private fun getFragmentItems(projectTabs: List<ProjectTab>): List<Fragment> {
        val fragments = mutableListOf<Fragment>()
        if (projectTabs != null) {
            for (projectTab in projectTabs) {
                fragments.add(ProjectPageFragment.newInstance(projectTab.id))
            }
        }
        return fragments
    }


    override fun initData() {
        super.initData()
        mPresent.getTabs()

    }
}