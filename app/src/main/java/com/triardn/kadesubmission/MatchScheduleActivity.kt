package com.triardn.kadesubmission

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.triardn.kadesubmission.fragment.NextMatchFragment
import com.triardn.kadesubmission.fragment.PreviousMatchFragment
import com.triardn.kadesubmission.model.Item
import com.triardn.kadesubmission.model.League
import kotlinx.android.synthetic.main.activity_match_schedule.*

class MatchScheduleActivity : AppCompatActivity() {
    var leagueID = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_schedule)

        val league = intent.getParcelableExtra<League>("EXTRA_ITEM")

        val actionbar = supportActionBar
        actionbar?.title = league?.strLeague
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val fragments = mutableListOf<Fragment>()
        val titles = mutableListOf<String>()
        leagueID = league?.idLeague.orEmpty()

        fragments.add(PreviousMatchFragment())
        titles.add("Previous Matches")
        fragments.add(NextMatchFragment())
        titles.add("Next Matches")

        viewPager.adapter = ViewPagerAdapter(fragments, titles, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    class ViewPagerAdapter(
        private val fragments: List<Fragment>,
        private val titles: List<String>,
        fragmentManager: FragmentManager
    ) : FragmentStatePagerAdapter(fragmentManager) {
        override fun getItem(position: Int) = fragments[position]

        override fun getCount() = fragments.size

        override fun getPageTitle(position: Int) = titles[position]
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

