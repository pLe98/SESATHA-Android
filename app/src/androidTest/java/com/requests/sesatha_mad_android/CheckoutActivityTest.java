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

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getTotal() {

        float result = checkoutActivity.getTotal((float)1400.00, (float)3500.00);
        assertEquals(result,(float)4900.00,0.01);
    }

    @Test
    public void getSubTotal() {
        float result = checkoutActivity.getSubTotal((float)1300.00, 5);
        assertEquals(result,(float)6500.00,0.01);
    }
}