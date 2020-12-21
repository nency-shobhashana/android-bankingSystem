package com.lambton.androidFinalProject.dto;

public class Customer {
    private long customerNo;
    private String customerName;
    private String accessCardNumber;
    private String pinNumber;

    public Customer(long customerNo,
            String customerName,
            String accessCardNumber,
            String pinNumber) {
        this.customerNo = customerNo;
        this.customerName = customerName;
        this.accessCardNumber = accessCardNumber;
        this.pinNumber = pinNumber;
    }

    public long getCustomerNo() {
        return customerNo;
    }

    public String getAccessCardNumber() {
        return accessCardNumber;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

}
