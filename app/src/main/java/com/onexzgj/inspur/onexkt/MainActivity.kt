package com.onexzgj.inspur.onexkt

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.onexzgj.inspur.onexkt.ui.home.HomeFragment
import com.onexzgj.inspur.onexkt.utils.StateBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    private lateinit var mAdapter: MainViewPagerAdapter
    lateinit var mContext: Context

    val tabs = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StateBar.fitSystemBar(this)
        setContentView(R.layout.activity_main)

        mContext = this
        tabs.add("首页")
        tabs.add("发现")
        initData()

    }

    private fun initData() {
        val list = mutableListOf<Fragment>()
        list.add(HomeFragment.newInstance())
        list.add(HomeFragment.newInstance())

        mAdapter = MainViewPagerAdapter(this, list)
        view_pager.adapter = mAdapter

        //viewPager2 就不能和再用TabLayout.setUpWithViewPager()了
//取而代之的是TabLayoutMediator。我们可以在onConfigureTab()方法的回调里面 做tab标签的配置
//其中autoRefresh的意思是:如果viewPager2 中child的数量发生了变化，也即我们调用了adapter#notifyItemChanged()前后getItemCount不同。
//要不要 重新刷野tabLayout的tab标签。视情况而定,像咱们sofaFragment的tab数量一旦固定了是不会变的，传true/false  都问题不大
        var mediator =
            TabLayoutMediator(tab_layout, view_pager, true,
                TabConfigurationStrategy { tab, position -> tab.setCustomView(makeTabView(position)) })
        mediator.attach()

        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val tabCount: Int = tab_layout.getTabCount()
                for (i in 0 until tabCount) {
                    val tab: TabLayout.Tab? = tab_layout.getTabAt(i)
                    val customView = tab?.customView as TextView?
                    if (tab?.position == position) {
                        customView!!.textSize = 16f
                        customView.typeface = Typeface.DEFAULT_BOLD
                    } else {
                        customView!!.textSize = 14f
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
        tabView.text = tabs[position]
        tabView.textSize = 14f
        return tabView
    }
}
