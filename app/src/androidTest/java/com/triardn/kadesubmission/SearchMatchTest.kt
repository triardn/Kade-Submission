package com.triardn.kadesubmission

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.runner.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchMatchTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(SearchActivity::class.java)

    @Test
    fun testSearchMatch() {
        // search
        onView(withId(R.id.search_query)).check(matches(isDisplayed()))
        onView(withId(R.id.search_query)).perform(click())

        onView(withId(R.id.search_query)).perform(typeText("Barcelona"))
        onView(withId(R.id.btn_search)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_search)).perform(click())
        onView(withId(R.id.schedule_list)).check(matches(isDisplayed()))
        onView(withText("Barcelona")).check(matches(isDisplayed()))
        onView(withId(R.id.search_query)).perform(clearText())

        onView(withId(R.id.search_query)).perform(typeText("Arsenal"))
        onView(withId(R.id.btn_search)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_search)).perform(click())
        onView(withId(R.id.schedule_list)).check(matches(isDisplayed()))
        onView(withText("Arsenal")).check(matches(isDisplayed()))
        onView(withId(R.id.search_query)).perform(clearText())

        onView(withId(R.id.search_query)).perform(typeText("Chelsea"))
        onView(withId(R.id.btn_search)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_search)).perform(click())
        onView(withId(R.id.schedule_list)).check(matches(isDisplayed()))
        onView(withText("Chelsea")).check(matches(isDisplayed()))
        onView(withId(R.id.search_query)).perform(clearText())
    }
}