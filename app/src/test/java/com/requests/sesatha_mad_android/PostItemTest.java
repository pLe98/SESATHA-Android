package com.requests.sesatha_mad_android;

import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PostItemTest {

    private PostItem postItemActivity;
    private MyItemEditActivity editActivity;

    @Before
    public void setUp() throws Exception {
        postItemActivity = new PostItem();
        editActivity = new MyItemEditActivity();
    }

    @Test
    public void testFeeCalculator(){
        float fee = postItemActivity.calculateFee((float) 1578.50);
        assertEquals(fee,(float)157.85,0.01);
    }

    @Test
    public void testCategoryIndexer(){
        assertEquals(3,editActivity.getCatIndex("Home & living"));
        assertEquals(1,editActivity.getCatIndex("Art & collectibles"));
        assertEquals(0,editActivity.getCatIndex(""));
    }

    @After
    public void tearDown() throws Exception {
    }
}