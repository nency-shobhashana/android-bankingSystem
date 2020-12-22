package com.lambton.androidFinalProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lambton.androidFinalProject.dto.Account;
import com.lambton.androidFinalProject.dto.Customer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static Customer customer;

    TextView txtWelcome;
    Button btnTransfer, btnPayUtility, btnLogout;
    ListView listViewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDependency();
        initListener();

        txtWelcome.setText("Welcome " + customer.getCustomerName());

        initList();

        setTitle("Eco Earth Bank");
    }

    private void initDependency() {
        txtWelcome = findViewById(R.id.txtWelcome);
        btnTransfer = findViewById(R.id.btnTransfer);
        btnPayUtility = findViewById(R.id.btnPayUtility);
        listViewAccount = findViewById(R.id.listAccount);
        btnLogout = findViewById(R.id.btnLogout);
    }

    private void initListener() {
        btnTransfer.setOnClickListener(v -> {
            Intent intent = new Intent(this, TransferActivity.class);
            startActivity(intent);
        });
        btnPayUtility.setOnClickListener(v -> {
            Intent intent = new Intent(this, UtilityActivity.class);
            startActivity(intent);
        });
        btnLogout.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            MainActivity.customer = null;
        });
    }

    private void initList() {
        listViewAccount.setAdapter(new AccountsAdapter(this, getAccounts(customer.getCustomerNo())));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((ArrayAdapter)listViewAccount.getAdapter()).notifyDataSetChanged();
    }

    private ArrayList<Account> getAccounts(long customerNo) {
        return DataUtils.getAccounts(customerNo);
    }

    public class AccountsAdapter extends ArrayAdapter<Account> {
        public AccountsAdapter(Context context, ArrayList<Account> accounts) {
            super(context, 0, accounts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Account account = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_account, parent, false);
            }
            // Lookup view for data population
            TextView txtAccountNo = convertView.findViewById(R.id.txtAccountNo);
            TextView txtAccountType = convertView.findViewById(R.id.txtAccountType);
            TextView txtBalance = convertView.findViewById(R.id.txtBalance);
            // Populate the data into the template view using the data object
            txtAccountNo.setText(String.valueOf(account.getAccountNo()));
            txtAccountType.setText(String.valueOf(account.getAccType()));
            txtBalance.setText("CAD " + account.getBalance());
            // Return the completed view to render on screen
            return convertView;
        }
    }
}