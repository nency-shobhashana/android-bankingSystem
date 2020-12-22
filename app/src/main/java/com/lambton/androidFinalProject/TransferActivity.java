package com.lambton.androidFinalProject;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lambton.androidFinalProject.dto.Account;

import java.util.ArrayList;

public class TransferActivity extends AppCompatActivity implements View.OnClickListener {


    EditText txtSrcAccount, txtDstAccount, txtAmount;
    Button btnTransfer, btnCancel;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        initDependency();
        initListener();

        setTitle("Transfer Money");
    }

    private void initDependency() {
        txtSrcAccount = findViewById(R.id.txtSrcAccount);
        txtDstAccount = findViewById(R.id.txtDstAccount);
        txtAmount = findViewById(R.id.txtAmount);
        btnTransfer = findViewById(R.id.btnTransfer);
        btnCancel = findViewById(R.id.btnCancel);

        ArrayList<Account> accounts = DataUtils.getAccounts(MainActivity.customer.getCustomerNo());
        ArrayList<String> accountNumbers = new ArrayList<>();

        for (Account account : accounts) {
            accountNumbers.add(String.valueOf(account.getAccountNo()));
        }

        ArrayAdapter adapter =new ArrayAdapter(this, R.layout.list_item, accountNumbers);
        ((AutoCompleteTextView)txtSrcAccount).setAdapter(adapter);

    }

    private void initListener() {
        btnTransfer.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Utils.hideKeyboard(this);
        if (v == btnTransfer) {
            if(transfer()){
                onBackPressed();
            }
        }
        if (v == btnCancel) {
            onBackPressed();
        }
    }

    private void transferError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private boolean transfer() {
        if(TextUtils.isEmpty(txtSrcAccount.getText())
        || TextUtils.isEmpty(txtDstAccount.getText())
        || TextUtils.isEmpty(txtAmount.getText())){
            transferError("Field is empty");
            return false;
        }
        Account srcAccount = getAccount(txtSrcAccount.getText().toString());
        Account dstAccount = getAccount(txtDstAccount.getText().toString());
        double amount = Double.parseDouble(txtAmount.getText().toString());

        if(srcAccount == null){
            transferError("Wrong Source Account");
            return false;
        }
        if(dstAccount == null){
            transferError("Wrong Destination Account");
            return false;
        }
        if((srcAccount.getBalance() - amount) > 0) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            dstAccount.setBalance(dstAccount.getBalance() + amount);
        } else {
            transferError("Not having sufficient fund");
            return false;
        }

        if(!isSameClient(srcAccount, dstAccount)){
            sendEmail(srcAccount, dstAccount, amount);
        }

        Toast.makeText(getApplicationContext(), "Transfer done.", Toast.LENGTH_LONG).show();
        return true;
    }

    private void sendEmail(Account srcAccount, Account dstAccount, double amount) {
        String message = amount + " is transfer to your " + dstAccount.getAccountNo() + " account" +
                " from " + srcAccount.getAccountNo() + " account ";
        try {
            MailService.sendEmail("Money credited",
                    dstAccount.getEmailId(), message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Account getAccount(String accountId) {
        return DataUtils.getAccount(Long.parseLong(accountId));
    }

    private boolean isSameClient(Account srcAccount, Account dstAccount) {
        return srcAccount.getCustomerNo() == dstAccount.getCustomerNo();
    }

}