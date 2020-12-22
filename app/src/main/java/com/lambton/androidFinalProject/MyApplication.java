package com.lambton.androidFinalProject;

import android.app.Application;

import com.lambton.androidFinalProject.dto.Account;
import com.lambton.androidFinalProject.dto.Customer;

import java.util.ArrayList;

public class MyApplication extends Application {

    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static ArrayList<Account> accounts = new ArrayList<Account>();

    @Override
    public void onCreate() {
        super.onCreate();
        customers.add(new Customer(123, "User", "123456", "1234"));
        customers.add(new Customer(234, "Nency", "234567", "2345"));
        customers.add(new Customer(345, "Elmy", "345678", "3456"));
        customers.add(new Customer(456, "Mandeep", "456789", "4567"));

        accounts.add(new Account(1234, 123, "shobhashananency@gmail.com",1000, "Joint"));
        accounts.add(new Account(1235, 123, "shobhashananency@gmail.com",2000, "Saving"));
        accounts.add(new Account(1236, 234, "emailtonency@gmail.com",1500, "Saving"));
        accounts.add(new Account(1237, 234, "emailtonency@gmail.com",1600, "Current"));
        accounts.add(new Account(1238, 234, "emailtonency@gmail.com",1700, "Joint"));
        accounts.add(new Account(1239, 345, "elmysebatty001@gmail.com",3000, "Saving"));
        accounts.add(new Account(1240, 456, "cheemaman020@gmail.com",2500, "Joint"));
    }
}
