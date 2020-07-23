package com.example.fyp.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.fyp.Buyers.BuyerList;
import com.example.fyp.Warehouse.Warehouse;
import com.example.fyp.R;

public class AdminMenu extends AppCompatActivity {
    RelativeLayout relay_viewEmployees,relay_viewUsers,relay_adminTask,relay_roles,relay_AdminWarehouse,relay_AdminCustomers,relay_AdminProduct;
    String userIc,role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        Intent data = getIntent();
        userIc = data.getStringExtra("userIc");

        relay_viewEmployees = findViewById(R.id.relay_viewEmployees);
        relay_viewUsers = findViewById(R.id.relay_viewUsers);
        relay_adminTask = findViewById(R.id.relay_adminTask);
        relay_roles = findViewById(R.id.relay_roles);
        relay_AdminWarehouse = findViewById(R.id.relay_AdminWarehouse);
        relay_AdminCustomers = findViewById(R.id.relay_AdminCustomers);
        relay_AdminProduct = findViewById(R.id.relay_AdminProduct);

        relay_roles.setOnClickListener(e->{
            Intent intent = new Intent(AdminMenu.this, Roles.class);
            intent.putExtra("userIc",userIc);
            startActivity(intent);
        });

        relay_viewEmployees.setOnClickListener(e->{
            Intent intent = new Intent(AdminMenu.this, AdminEmployeeUser.class);
            intent.putExtra("userIc",userIc);
            intent.putExtra("check","employee");
            startActivity(intent);
        });
        relay_viewUsers.setOnClickListener(e->{
            Intent intent = new Intent(AdminMenu.this, AdminEmployeeUser.class);
            intent.putExtra("userIc",userIc);
            intent.putExtra("check","user");
            startActivity(intent);
        });
        relay_AdminCustomers.setOnClickListener(e->{
            Intent intent = new Intent(AdminMenu.this, BuyerList.class);
            intent.putExtra("userIc",userIc);
            intent.putExtra("check","admin");
            startActivity(intent);
        });
        relay_AdminWarehouse.setOnClickListener(e->{
                Intent intent = new Intent(AdminMenu.this, Warehouse.class);
                intent.putExtra("userIc",userIc);
                intent.putExtra("check","admin");
                startActivity(intent);
        });

        relay_AdminProduct.setOnClickListener(e->{
            Intent intent = new Intent(AdminMenu.this, AdminProduct.class);
            intent.putExtra("userIc",userIc);
            intent.putExtra("check","admin");
            startActivity(intent);
        });
    }
}
