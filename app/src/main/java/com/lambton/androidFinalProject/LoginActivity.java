package com.lambton.androidFinalProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lambton.androidFinalProject.dto.Customer;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtAccessCardNo, txtPin;
    Button btnLogin, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initDependency();
        initListener();

        setTitle("");
    }

    private void initDependency() {
        txtAccessCardNo = findViewById(R.id.txtAccessCardNo);
        txtPin = findViewById(R.id.txtPin);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
    }

    private void initListener() {
        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            if(Login()){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
        if (v == btnCancel) {
            onBackPressed();
        }
    }

    private boolean Login() {
        if(TextUtils.isEmpty(txtAccessCardNo.getText())
                || TextUtils.isEmpty(txtPin.getText())){
            loginError("Field is empty");
            return false;
        }

        Customer customer = DataUtils.getCustomer(txtAccessCardNo.getText().toString());

        if(customer == null || !customer.getPinNumber().equals(txtPin.getText().toString())){
            loginError("Credential is incorrect");
            return false;
        }

        MainActivity.customer = customer;
        return true;
    }

    private void loginError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


}