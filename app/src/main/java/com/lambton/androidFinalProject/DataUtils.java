package com.lambton.androidFinalProject;

import com.lambton.androidFinalProject.dto.Account;
import com.lambton.androidFinalProject.dto.Customer;

import java.util.ArrayList;

public class DataUtils {

    public static Customer getCustomer(String accessCardNo) {
        for (Customer customer : MyApplication.customers) {
            if(customer.getAccessCardNumber().equals(accessCardNo)){
                return customer;
            }
        }
        return null;
    }

    public static ArrayList<Account> getAccounts(long customerNo){
        ArrayList<Account> accounts = new ArrayList<Account>();
        for (Account account : MyApplication.accounts) {
            if(account.getCustomerNo() == customerNo){
                accounts.add(account);
            }
        }
        return accounts;
    }

    public static Account getAccount(long accountNo) {
        for (Account account : MyApplication.accounts) {
            if(account.getAccountNo() == accountNo){
                return account;
            }
        }
        return null;
    }
}
