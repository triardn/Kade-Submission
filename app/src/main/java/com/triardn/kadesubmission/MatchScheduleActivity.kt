package com.triardn.kadesubmission

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.triardn.kadesubmission.fragment.PreviousMatchFragment
import kotlinx.android.synthetic.main.activity_match_schedule.*

class MatchScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_schedule)

        val fragments = mutableListOf<PreviousMatchFragment>()
         fragments.add(PreviousMatchFragment())

        viewPager.adapter = ViewPagerAdapter(fragments, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    class ViewPagerAdapter(
        private val fragments: List<Fragment>,
        fragmentManager: FragmentManager
    ) : FragmentStatePagerAdapter(fragmentManager) {
        override fun getItem(position: Int) = fragments[position]

        override fun getCount() = fragments.size

        override fun getPageTitle(position: Int) = "asd"
    }
}

