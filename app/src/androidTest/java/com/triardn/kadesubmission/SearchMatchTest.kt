package com.triardn.kadesubmission

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class SearchMatchTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(SearchActivity::class.java)

    @Test
    fun testSearchMatch() {
        // search
        onView(withId(R.id.action_search_menu)).check(matches(isDisplayed()))
        onView(withId(R.id.action_search_menu)).check(matches(isDisplayed()))
        onView(withId(R.id.action_search_menu)).perform(click())
        onView(withText("Barcelona")).perform(click())
        clearText()
        onView(withText("Chelsea")).perform(click())
        clearText()
        onView(withText("Arsenal")).perform(click())
        clearText()
    }
}