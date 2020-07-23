package com.example.fyp.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.fyp.BackEndServer.RetrofitClient;
import com.example.fyp.Class.User;
import com.example.fyp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminEmployeeUser extends AppCompatActivity {
    ArrayList<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_employee_user);
        TextView testname = (TextView)findViewById(R.id.testname);
        TextView nameList = (TextView)findViewById(R.id.nameList);
        SwipeMenuListView employeelist = (SwipeMenuListView) findViewById(R.id.employeeList);
        Button refreshList = (Button)findViewById(R.id.refreshList);
        refreshList.setOnClickListener(e->{
            finish();
            startActivity(getIntent());
        });
        Intent data = getIntent();
        list = new ArrayList<>();
        if(data.getStringExtra("check").equals("employee")) {
            getEmployees();
        }else{
            nameList.setText("Users List");
            getUsers();
        }
        refreshList.setOnClickListener(e->{
            list.clear();
            finish();
            startActivity(getIntent());
        });

        AdminEmployeeUserAdapter ListAdapterListAdapter = new AdminEmployeeUserAdapter(this,R.layout.admin_employee_user_list,list);
        employeelist.setAdapter(ListAdapterListAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(150);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        employeelist.setMenuCreator(creator);
        employeelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(AdminEmployeeUser.this, EmployeeUserDetails.class);
                intent.putExtra("userIc",data.getStringExtra("userIc"));
                intent.putExtra("Name",list.get(i).getFirstName());
                intent.putExtra("Ic",list.get(i).getIC());
                intent.putExtra("Contact",list.get(i).getContact());
                intent.putExtra("Address",list.get(i).getAddress1()+"\n"+list.get(i).getAddress2());
                intent.putExtra("Role",list.get(i).getRole());
                intent.putExtra("TotalSalary",list.get(i).getSalary());
                intent.putExtra("check",data.getStringExtra("check"));
                startActivity(intent);
            }
        });

        employeelist.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // delete
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(AdminEmployeeUser.this);
                        View mView = getLayoutInflater().inflate(R.layout.dialog_delete, null);
                        //TextView companyName = (TextView) mView.findViewById(R.id.companyName);
                        Button yes = (Button) mView.findViewById(R.id.deleteYes);
                        Button no = (Button) mView.findViewById(R.id.deleteNo);
                        mBuilder.setView(mView);
                        AlertDialog dialog = mBuilder.create();
                        dialog.show();
                        no.setOnClickListener(a -> {
                            dialog.dismiss();
                        });
                        yes.setOnClickListener(e -> {
                            User user = new User(list.get(position).getIC());
                            deleteUser(user);
                            list.clear();
                            finish();
                            startActivity(getIntent());
                        });
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }
    private void getEmployees(){
        Call<List<User>> call = RetrofitClient.getInstance().getApi().findAllEmployees();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> employees = response.body();
                for (User currentEmployee : employees) {
                    list.add(currentEmployee);
                    // testname.append(currentEmployee.getIC());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
    private void getUsers(){
        Call<List<User>> call = RetrofitClient.getInstance().getApi().findAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                for (User currentUsers : users) {
                    list.add(currentUsers);
                    // testname.append(currentEmployee.getIC());
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
    private void deleteUser(User user){
        Call<User> call = RetrofitClient.getInstance().getApi().deleteUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code()==200){
                    Toast.makeText(getApplicationContext(), "Delete Successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Delete Fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
