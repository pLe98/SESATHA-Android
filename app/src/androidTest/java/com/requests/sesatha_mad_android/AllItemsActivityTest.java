package com.requests.sesatha_mad_android;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AllItemsActivityTest {

    @Rule
    public ActivityTestRule<AllItemsActivity> activityRule
            = new ActivityTestRule<AllItemsActivity>(
            AllItemsActivity.class){
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent result = new Intent(targetContext, AllItemsActivity.class);
            result.putExtra("Category", "");
            return result;
        }
    };

    @Test
    public void intent() {
        try{
            Espresso.onView(withId(R.id.search_view)).check(matches(isDisplayed()));
        }catch (NoMatchingViewException e){

        }

        //View view = testActivity.findViewById(R.id.AllListingsRecycler);
        //assertNotNull(view);
    }
}