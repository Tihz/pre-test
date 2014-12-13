package com.priceminister.account.implementation;

import org.apache.commons.lang3.ObjectUtils;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.IllegalBalanceException;

public class CustomerAccount implements Account {
    
    private Double balance = 0d;

    public void add(Double addedAmount) {
        Double amount = ObjectUtils.defaultIfNull(addedAmount, 0d);
        
        checkNegativeAmount(amount);
        
        balance += amount;
    }
    
    private void checkNegativeAmount(Double amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount cannot be negative");
    }

    public Double getBalance() {
        return balance;
    }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) 
            throws IllegalBalanceException {

        if (rule == null)
            throw new IllegalArgumentException("Rule cannot be null");

        Double amount = ObjectUtils.defaultIfNull(withdrawnAmount, 0d);
        checkNegativeAmount(amount);
        
        Double newBalance = balance - amount;
        
        checkRule(rule, newBalance);

        balance = newBalance;
        return balance;
    }

    private void checkRule(AccountRule rule, Double newBalance)
            throws IllegalBalanceException {
        if (!rule.withdrawPermitted(newBalance))
            throw new IllegalBalanceException(newBalance);
    }
}
