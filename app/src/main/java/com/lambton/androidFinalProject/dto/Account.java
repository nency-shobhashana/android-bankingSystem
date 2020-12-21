package com.lambton.androidFinalProject.dto;

public class Account {
    private long accountNo;
    private long customerNo;
    private String emailId;
    private double balance;
    private String accType;

    public Account(long accountNo,
            long customerNo,
            String emailId,
            double balance,
            String accType) {
        this.accountNo = accountNo;
        this.customerNo = customerNo;
        this.emailId = emailId;
        this.balance = balance;
        this.accType = accType;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public long getCustomerNo() {
        return customerNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccType() {
        return accType;
    }
}
