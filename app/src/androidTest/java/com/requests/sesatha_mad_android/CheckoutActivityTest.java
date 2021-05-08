package com.requests.sesatha_mad_android;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckoutActivityTest {

    private CheckoutActivity checkoutActivity;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getTotal() {
        float result = checkoutActivity.getTotal(1200, 120);
        assertEquals(1320, result);
    }

    @Test
    public void getSubTotal() {
        float result = checkoutActivity.getTotal(1200, 2);
        assertEquals(2400, result);
    }

    @After
    public void tearDown() throws Exception {
    }


}