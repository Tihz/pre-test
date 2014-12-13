package com.priceminister.account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountRule;

/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {

	Account customerAccount;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		customerAccount = new CustomerAccount();
		
	}

	/**
	 * Tests that an empty account always has a balance of 0.0, not a NULL.
	 */
	@Test
	public void testAccountWithoutMoneyHasZeroBalance() {
		Double balance = customerAccount.getBalance();
		Assert.assertEquals(balance, new Double(0));
	}
	
	private Double addAmount(Double amount) {
        customerAccount.add(amount);
        return customerAccount.getBalance();
    }

    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() {
        Double amount = 1d;
        Double balance = addAmount(amount);
        Assert.assertEquals(balance, amount);
    }

    /**
     * Adds a negative amount to the account and checks that throw a IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNegativeAmount() {
        Double amount = -1d;
        addAmount(amount);
    }
    
    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddNullAmount() {
        Double amount = null;
        Double balance = addAmount(amount);
        Assert.assertEquals(balance, new Double(0));
    }

	/**
	 * Tests that an illegal withdrawal throws the expected exception.
	 * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
	 * 
	 * @throws IllegalBalanceException
	 */
	@Test(expected = IllegalBalanceException.class)
	public void testWithdrawAndReportBalanceIllegalBalance()
			throws IllegalBalanceException {
		Double amount = 1d;
		AccountRule rule = new CustomerAccountRule();
		customerAccount.withdrawAndReportBalance(amount, rule);
	}
	
    /**
     * Tests that a negative amount throws a IllegalArgumentException.
     * 
     * @throws IllegalBalanceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawAndReportBalanceNegativeAmount() 
            throws IllegalBalanceException {
        Double amount = -1d;
        AccountRule rule = new CustomerAccountRule();
        customerAccount.withdrawAndReportBalance(amount, rule);
    }
    
    /**
     * Tests that a null amount don't throw any exception and has no effect on balance
     * 
     * @throws IllegalBalanceException
     */
    public void testWithdrawAndReportBalanceNullAmount() 
            throws IllegalBalanceException {
        Double amount = null;
        AccountRule rule = null;
        Double balance = customerAccount.withdrawAndReportBalance(amount, rule);
        Assert.assertEquals(balance, new Double(0));
    }
    
    /**
     * Tests that a null rule throws a IllegalArgumentException.
     * 
     * @throws IllegalBalanceException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawAndReportBalanceNullRule() 
            throws IllegalBalanceException {
        Double amount = 0d;
        AccountRule rule = null;
        customerAccount.withdrawAndReportBalance(amount, rule);
    }
}
