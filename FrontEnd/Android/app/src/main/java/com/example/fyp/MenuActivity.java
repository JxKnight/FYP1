package com.example.fyp;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fyp.Admin.AdminMenu;
import com.example.fyp.BackEndServer.RetrofitClient;
import com.example.fyp.Class.Role;
import com.example.fyp.Function.Attendance;
import com.example.fyp.Buyers.BuyerList;
import com.example.fyp.Products.Products;
import com.example.fyp.Warehouse.Warehouse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuActivity extends AppCompatActivity{
    RelativeLayout relay_userProfile,relay_products,relay_warehouse,relay_customers,relay_tasks,relay_employeeAttendance,relay_Admin,relay_reports;
    String userIc,role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent data = getIntent();
        userIc = data.getStringExtra("userIc");
        role = data.getStringExtra("userRole");

        relay_userProfile = findViewById(R.id.relay_userProfile);
        relay_products = findViewById(R.id.relay_products);
        relay_warehouse = findViewById(R.id.relay_warehouse);
        relay_customers = findViewById(R.id.relay_customers);
        relay_tasks = findViewById(R.id.relay_tasks);
        relay_employeeAttendance = findViewById(R.id.relay_employeeAttendance);
        relay_Admin = findViewById(R.id.relay_Admin);
        relay_reports = findViewById(R.id.relay_reports);

            Role getRole = new Role(role);
            Call<Role> call = RetrofitClient.getInstance().getApi().getRolePrivileges(getRole);
            call.enqueue(new Callback<Role>() {
                @Override
                public void onResponse(Call<Role> call, Response<Role> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                    } else {
                        //tv.append("valid");
                        Role role = response.body();
                        if (role.getOrders().equals("0")) {
                            relay_products.setBackgroundColor(Color.argb(220, 220, 220, 220));
                            relay_products.setEnabled(false);
                        }
                        if (role.getWarehouse().equals("0")) {
                            relay_warehouse.setBackgroundColor(Color.argb(220, 220, 220, 220));
                            relay_warehouse.setEnabled(false);
                        }
                        if (role.getCustomers().equals("0")) {
                            relay_customers.setBackgroundColor(Color.argb(220, 220, 220, 220));
                            relay_customers.setEnabled(false);
                        }
                        if (role.getTasks().equals("0")) {
                            relay_tasks.setBackgroundColor(Color.argb(220, 220, 220, 220));
                            relay_tasks.setEnabled(false);
                        }
                        if (role.getReports().equals("0")) {
                            relay_reports.setBackgroundColor(Color.argb(220, 220, 220, 220));
                            relay_reports.setEnabled(false);
                        }
                        if (role.getRoleName().equals("admin")) {

                        } else {
                            relay_Admin.setBackgroundColor(Color.argb(220, 220, 220, 220));
                            relay_Admin.setEnabled(false);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Role> call, Throwable t) {

                }
            });

        relay_userProfile.setOnClickListener(e->{
            Intent intent = new Intent(MenuActivity.this,Profile_Register.class);
            intent.putExtra("userIc",userIc);
            intent.putExtra("check","false");
            startActivity(intent);
        });

        relay_products.setOnClickListener(e->{
            Intent intent = new Intent(MenuActivity.this, Products.class);
            intent.putExtra("userIc",userIc);
            startActivity(intent);
        });

        relay_warehouse.setOnClickListener(e->{
                Intent intent = new Intent(MenuActivity.this, Warehouse.class);
                intent.putExtra("userIc",userIc);
                intent.putExtra("check","user");
                startActivity(intent);
        });

        relay_Admin.setOnClickListener(e->{
            Intent intent = new Intent(MenuActivity.this, AdminMenu.class);
            intent.putExtra("userIc",userIc);
            startActivity(intent);
        });

        relay_employeeAttendance.setOnClickListener(e->{
            Intent intent = new Intent(MenuActivity.this, Attendance.class);
            intent.putExtra("userIc",userIc);
            startActivity(intent);
        });

        relay_customers.setOnClickListener(e->{
            Intent intent = new Intent(MenuActivity.this, BuyerList.class);
            intent.putExtra("userIc",userIc);
            intent.putExtra("check","user");
            startActivity(intent);
        });
    }
}
