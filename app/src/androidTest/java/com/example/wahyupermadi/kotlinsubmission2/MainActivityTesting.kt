package com.example.wahyupermadi.kotlinsubmission2

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.pressBack
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.wahyupermadi.kotlinsubmission2.R.id.rv_prevmatch
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTesting {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehavior() {
        Espresso.onView(ViewMatchers.withId(rv_prevmatch))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(rv_prevmatch))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))

        Espresso.onView(ViewMatchers.withId(rv_prevmatch))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, ViewActions.click()))

        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.nm_imghomedetail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.nm_awayimgdetail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}