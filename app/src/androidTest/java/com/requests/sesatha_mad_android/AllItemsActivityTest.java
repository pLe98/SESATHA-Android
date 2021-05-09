package com.requests.sesatha_mad_android;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AllItemsActivityTest {

    @Rule
    public ActivityTestRule<AllItemsActivity> activityRule
            = new ActivityTestRule<AllItemsActivity>(
            AllItemsActivity.class);
    private AllItemsActivity testActivity = null;

    @Before
    public void setUp() throws Exception{
        testActivity =  activityRule.getActivity();
    }


    @Test
    public void intent() {
        View view = testActivity.findViewById(R.id.AllListingsRecycler);
        assertNotNull(view);
    }
}