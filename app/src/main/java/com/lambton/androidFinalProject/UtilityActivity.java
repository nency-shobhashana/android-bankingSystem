package com.lambton.androidFinalProject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lambton.androidFinalProject.dto.Account;
import com.lambton.androidFinalProject.dto.Customer;

import java.util.ArrayList;

public class UtilityActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    Spinner accSp, choiceSp;
    EditText num, amount;
    Button pay;
    RadioButton rb1, rb2;
    TextView message, subscriptionId;

    ArrayList<String> utilityChoices = new ArrayList<>();
    ArrayList<String> accountNumber = new ArrayList<>();
    ArrayList<Customer> customerDetails = new ArrayList<>();

    String accountNum = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility);

        setTitle("Pay Utility");

        accSp = findViewById(R.id.accountSpinner);
        choiceSp = findViewById(R.id.choiceSp);
        num = findViewById(R.id.subscription);
        amount = findViewById(R.id.amount);
        pay = findViewById(R.id.pay);
        rb1 = findViewById(R.id.yes);
        rb2 = findViewById(R.id.no);
        message = findViewById(R.id.messages);
        subscriptionId = findViewById(R.id.subId);

        fillUtility();
        for (Account account : DataUtils.getAccounts(0)) {
            accountNumber.add(String.valueOf(account.getAccountNo()));
        }

        ArrayAdapter aa1 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                utilityChoices);
        choiceSp.setAdapter(aa1);
        choiceSp.setOnItemSelectedListener(this);

        ArrayList<Account> accounts = DataUtils.getAccounts(MainActivity.customer.getCustomerNo());

        accountNumber.clear();
        for (Account account : accounts) {
            accountNumber.add(String.valueOf(account.getAccountNo()));
        }

        ArrayAdapter aa2 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                accountNumber);
        accSp.setAdapter(aa2);
        accSp.setOnItemSelectedListener(this);

        rb1.setOnClickListener(new RadioButtonsAction());
        rb2.setOnClickListener(new RadioButtonsAction());

        rb1.setVisibility(View.INVISIBLE);
        rb2.setVisibility(View.INVISIBLE);
        message.setVisibility(View.INVISIBLE);

        pay.setOnClickListener(view -> {
            Utils.hideKeyboard(this);
            if (!subscriptionId.getText().toString().isEmpty() &&
                    !amount.getText().toString().isEmpty()) {

                Account account = DataUtils.getAccount(Long.parseLong(accountNum));

                Double balance = account.getBalance();
                Double paidAmount = Double.parseDouble(amount.getText().toString());

                if (balance >= paidAmount) {
                    account.setBalance(balance - paidAmount);
                    String amount = String.valueOf(account.getBalance());
                    Toast.makeText(UtilityActivity.this,
                            "Available balance is " + amount,
                            Toast.LENGTH_SHORT).show();
                    rb1.setVisibility(View.VISIBLE);
                    rb2.setVisibility(View.VISIBLE);
                    message.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(UtilityActivity.this,
                            "Insufficient balance",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(UtilityActivity.this,
                        "All fields are necessary",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillUtility() {
        utilityChoices.add("Hydro");
        utilityChoices.add("Water");
        utilityChoices.add("Gas");
        utilityChoices.add("Phone");

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (view.getId() == R.id.choiceSp) {
            if (utilityChoices.get(position).equals("Phone")) {
                subscriptionId.setText("Phone number");
            } else {
                subscriptionId.setText("Subscription no.");
            }
        } else {
            accountNum = accountNumber.get(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class RadioButtonsAction implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.yes) {
                Toast.makeText(UtilityActivity.this,
                        "Subscription number is saved successfully",
                        Toast.LENGTH_SHORT).show();
            } else if (view.getId() == R.id.no) {
                Toast.makeText(UtilityActivity.this,
                        "Subscription number is not saved",
                        Toast.LENGTH_SHORT).show();
            }
            rb1.setVisibility(View.INVISIBLE);
            rb2.setVisibility(View.INVISIBLE);
            message.setVisibility(View.INVISIBLE);
            onBackPressed();
        }
    }
}