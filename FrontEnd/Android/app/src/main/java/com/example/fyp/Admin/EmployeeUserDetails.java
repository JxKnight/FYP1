package com.example.fyp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.BackEndServer.RetrofitClient;
import com.example.fyp.Class.Location;
import com.example.fyp.Class.Role;
import com.example.fyp.Class.User;
import com.example.fyp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeUserDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_user_details);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.7));

        List<String> roles = new ArrayList<>();
        roles.add("Select Roles");
        LinearLayout detailsRole = (LinearLayout)findViewById(R.id.detailsRole);
        TextView name = (TextView)findViewById(R.id.name);
        TextView ic = (TextView)findViewById(R.id.ic);
        TextView contact = (TextView)findViewById(R.id.contact);
        TextView Address = (TextView)findViewById(R.id.Address);
        TextView Roles = (TextView)findViewById(R.id.Roles);
        TextView TotalSalary = (TextView)findViewById(R.id.TotalSalary);
        TextView TotalSalaryTV = (TextView)findViewById(R.id.TotalSalaryTV);
        Spinner RolesSpinner = (Spinner)findViewById(R.id.RolesSpinner);
        Button Update = (Button)findViewById(R.id.Update);
        Intent data = getIntent();
        Call<List<Role>> call = RetrofitClient.getInstance().getApi().findAllRoles();
        call.enqueue(new Callback<List<Role>>() {
            @Override
            public void onResponse(Call<List<Role>> call, Response<List<Role>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.code(),Toast.LENGTH_LONG).show();
                    return;
                }
                List<Role> roles1 = response.body();
                for(Role currentRole: roles1){
                    roles.add(currentRole.getRoleName());
                }
            }
            @Override
            public void onFailure(Call<List<Role>> call, Throwable t) {

            }
        });

        ArrayAdapter<String> roleAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,roles);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RolesSpinner.setAdapter(roleAdapter);
        RolesSpinner.setOnItemSelectedListener(this);
        if(data.getStringExtra("check").equals("employee")){
            name.setText(data.getStringExtra("Name"));
            ic.setText(data.getStringExtra("Ic"));
            contact.setText(data.getStringExtra("Contact"));
            Address.setText(data.getStringExtra("Address"));
            Roles.setText(data.getStringExtra("Role"));
            TotalSalary.setText(data.getStringExtra("TotalSalary"));
        }else{
            TotalSalary.setText("");
            TotalSalaryTV.setText("");
            name.setText(data.getStringExtra("Name"));
            ic.setText(data.getStringExtra("Ic"));
            contact.setText(data.getStringExtra("Contact"));
            Address.setText(data.getStringExtra("Address"));
            Roles.setText(data.getStringExtra("Role"));
        }
        Update.setOnClickListener(e->{
            String x = RolesSpinner.getSelectedItem().toString();
            if(x.equals("Select Roles")) {
                Toast.makeText(getApplicationContext(), "Please Select Roles", Toast.LENGTH_LONG).show();
            }else{
                User user = new User(data.getStringExtra("Contact"), data.getStringExtra("Ic"), x);
                Call<User> updateURole = RetrofitClient.getInstance().getApi().updateUserRole(user);
                updateURole.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Update Fail", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
