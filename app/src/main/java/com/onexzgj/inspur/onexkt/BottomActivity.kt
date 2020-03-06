package com.onexzgj.inspur.onexkt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode.LABEL_VISIBILITY_LABELED
import com.onexzgj.inspur.onexkt.ui.project.ProjectFragment
import com.onexzgj.inspur.onexkt.ui.home.HomeFragment
import com.onexzgj.inspur.onexkt.ui.notifications.NotificationsFragment
import com.onexzgj.inspur.onexkt.utils.StateBar
import kotlinx.android.synthetic.main.activity_bottom.*

class BottomActivity : AppCompatActivity() {

    private var index: Int = 0

    private var fragmentTag: String? = null

    private var mCurrentFragment: Fragment? = null


    private val fragmentNames = arrayOf(
        HomeFragment::class.java.name,
        ProjectFragment::class.java.name,
        NotificationsFragment::class.java.name
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StateBar.fitSystemBar(this)
        setContentView(R.layout.activity_bottom)
        initNavBottom()
        bottomNav()
    }

    private fun initNavBottom() {
        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> index = 0
                    R.id.navigation_project -> index = 1
                    R.id.navigation_notifications -> index = 2
                }
                bottomNav()
                true
            }
        nav_view.run {
            labelVisibilityMode = LABEL_VISIBILITY_LABELED
            setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        }
    }

    private fun bottomNav() {
//        toolbar.title = getString(
//            if (index == 0) {
//                R.string.app_name
//            } else {
//                bottomTitles[index]
//            }
//        )
        fragmentTag = fragmentNames[index]
        val fragment = getFragmentByTag(fragmentTag!!)
        showFragment(mCurrentFragment, fragment, fragmentTag!!)
    }

    private fun getFragmentByTag(name: String): Fragment {
        var fragment = supportFragmentManager.findFragmentByTag(name)
        if (fragment != null) {
            return fragment
        } else {
            try {
                fragment = Class.forName(name).newInstance() as Fragment
            } catch (e: Exception) {
                fragment = HomeFragment()
            }
        }
        return fragment!!
    }

    private fun showFragment(from: Fragment?, to: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        if (from == null) {
            if (to.isAdded) {
                transaction.show(to)
            } else {
                transaction.add(R.id.container, to, tag)
            }
        } else {
            if (to.isAdded) {
                transaction.hide(from).show(to)
            } else {
                transaction.hide(from).add(R.id.container, to, tag)
            }
        }
        transaction.commit()
        mCurrentFragment = to
    }


}
