package com.example.michael.myapplication;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import android.support.test.espresso.contrib.RecyclerViewActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by michael on 10/12/2018.
 */
@RunWith(AndroidJUnit4.class)
public class MainFragmentTest {
    @Rule public ActivityTestRule<MainActivity> _ActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void click_listItem() {
        // First, scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.rv_myData))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,
                        click()));

        // Match the text in an item below the fold and check that it's displayed.
        onView(withText("Lars")).check(matches(isDisplayed()));
        onView(withId(R.id.tv_lastname)).check(matches(withText("Struyf")));
    }

    //TODO: addbutton test
    //TODO: land error
    //TODO: query orderBy

}
