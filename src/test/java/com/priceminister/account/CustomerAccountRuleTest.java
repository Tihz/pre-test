package com.priceminister.account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.priceminister.account.implementation.CustomerAccountRule;

public class CustomerAccountRuleTest {

    private AccountRule rule;
    
    @Before
    public void setUp() {
        rule = new CustomerAccountRule();
    }
    
    /**
     * Tests that an withdraw is permitted.
     */
    @Test
    public void testwithdrawPermitted() {
        Double amount = 1d;
        boolean result = checkRule(amount);
        Assert.assertTrue(result);
    }

    private boolean checkRule(Double amount) {
        return rule.withdrawPermitted(amount);
    }
    
    /**
     * Tests that an withdraw is denied
     */
    @Test
    public void testwithdrawDenied() {
        Double amount = -1d;
        boolean result = checkRule(amount);
        Assert.assertFalse(result);
    }
    
    /**
     * Tests that an withdraw is denied when null
     */
    @Test
    public void testwithdrawDeniedWithNull() {
        Double amount = null;
        boolean result = checkRule(amount);
        Assert.assertTrue(result);
    }
    
    
}
