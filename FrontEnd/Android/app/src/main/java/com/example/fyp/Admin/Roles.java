package com.example.fyp.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.fyp.BackEndServer.RetrofitClient;
import com.example.fyp.Class.Role;
import com.example.fyp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Roles extends AppCompatActivity {

    private EditText rolesNameET,rolesDescriptionET,rolesRateET;
    private LinearLayout left,right;
    private Button btn,btn1;
    private List<String> checkBoxList;
    private List<String> checkedList;
    private SwipeMenuListView rolesList;
    private ArrayList <Role> roleList;
    private TextView companyName;
    private Boolean update = true;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles);

        rolesNameET = (EditText)findViewById(R.id.rolesNameET);
        rolesDescriptionET = (EditText)findViewById(R.id.rolesDescriptionET);
        rolesRateET = (EditText)findViewById(R.id.rolesRateET);
        left = (LinearLayout)findViewById(R.id.rolesCBLeft);
        right = (LinearLayout)findViewById(R.id.rolesCBRight);
        btn = (Button)findViewById(R.id.addRoles);
        btn1 = (Button)findViewById(R.id.RolesList);
        rolesList = (SwipeMenuListView) findViewById(R.id.rolesList);
        companyName=(TextView)findViewById(R.id.companyName);

         checkBoxList = new ArrayList<>();
         checkedList = new ArrayList<>();
         checkBoxList.add("warehouse");
         checkBoxList.add("orders");
         checkBoxList.add("tasks");
         checkBoxList.add("reports");
         checkBoxList.add("customers");
        for(int i=0;i<checkBoxList.size();i++) {
            CheckBox cb = new CheckBox(this);
            if (i < 3) {
                cb.setText(checkBoxList.get(i));
                cb.setTag(checkBoxList.get(i));
                cb.setId(i);
                left.addView(cb);
            } else {
                cb.setText(checkBoxList.get(i));
                cb.setTag(checkBoxList.get(i));
                cb.setId(i);
                right.addView(cb);
            }
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        checkedList.add(compoundButton.getTag().toString());
                    }else{
                        for(int i=0;i<checkedList.size();i++) {
                            if(checkedList.get(i).equals(compoundButton.getTag().toString())) {
                                checkedList.remove(i);
                            }
                        }
                    }
                }
            });
        }
            roleList= new ArrayList<>();
            getAllRoles();
            RolesListAdapter rolesListAdapter = new RolesListAdapter(this,R.layout.roles_list,roleList);
            rolesList.setAdapter(rolesListAdapter);

         SwipeMenuCreator creator = new SwipeMenuCreator() {

             @Override
             public void create(SwipeMenu menu) {
//                 // create "open" item
//                 SwipeMenuItem openItem = new SwipeMenuItem(
//                         getApplicationContext());
//                 // set item background
//                 openItem.setBackground(new ColorDrawable(Color.rgb(0x0A, 0xCC,
//                         0xF7)));
//                 // set item width
//                 openItem.setWidth(150);
//                 // set item title
//                 openItem.setTitle("Edit");
//                 // set item title fontsize
//                 openItem.setTitleSize(18);
//                 // set item title font color
//                 openItem.setTitleColor(Color.WHITE);
//                 // add to menu
//                 menu.addMenuItem(openItem);

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
         rolesList.setMenuCreator(creator);
         rolesList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
             @Override
             public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                 switch (index) {
                     case 0:
                         // delete
                         if(roleList.get(position).getRoleName().equals("admin")){
                             Toast.makeText(getApplicationContext(), "Unable to delete admin role", Toast.LENGTH_LONG).show();
                         }else {
                             //Toast.makeText(getApplicationContext(), roleList.get(index).getRoleName(), Toast.LENGTH_LONG).show();
                             AlertDialog.Builder mBuilder = new AlertDialog.Builder(Roles.this);
                             View mView = getLayoutInflater().inflate(R.layout.dialog_delete, null);
                             TextView companyName = (TextView) mView.findViewById(R.id.companyName);
                             Button yes = (Button) mView.findViewById(R.id.deleteYes);
                             Button no = (Button) mView.findViewById(R.id.deleteNo);
                             mBuilder.setView(mView);
                             AlertDialog dialog = mBuilder.create();
                             dialog.show();
                             no.setOnClickListener(a -> {
                                 dialog.dismiss();
                             });
                             yes.setOnClickListener(e -> {
                                 Role role = new Role(roleList.get(position).getRoleName());
                                 deleteRole(role);
                                 roleList.clear();
                                 finish();
                                 startActivity(getIntent());
                             });
                             break;
                         }
                 }
                 // false : close the menu; true : not close the menu
                 return false;
             }
         });

        btn.setOnClickListener(e->{
            String warehouse = "0",orders="0",customers="0",reports="0",tasks="0";
            for(String currentCheck: checkedList) {
                if(currentCheck.equals("warehouse")){
                    warehouse="1";
                }
                if(currentCheck.equals("orders")){
                    orders="1";
                }
                if(currentCheck.equals("customers")){
                    customers="1";
                }
                if(currentCheck.equals("reports")){
                    reports="1";
                }
                if(currentCheck.equals("tasks")){
                    tasks="1";
                }
            }
            Role role = new Role(rolesNameET.getText().toString(),rolesDescriptionET.getText().toString(),rolesRateET.getText().toString(),warehouse,orders,customers,reports,tasks);
            createRole(role);
        });

        btn1.setOnClickListener(e->{
            roleList.clear();
            finish();
            startActivity(getIntent());
        });

    }

    private void createRole(Role role){
        Call<Role> call = RetrofitClient.getInstance().getApi().createRoles(role);
        call.enqueue(new Callback<Role>() {
            @Override
            public void onResponse(Call<Role> call, Response<Role> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Create Role UnSuccessful, \nDuplicate Role Name Exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Role> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Create Role Successful", Toast.LENGTH_LONG).show();
                finish();
                startActivity(getIntent());
            }
        });
    }

    private void deleteRole(Role role){
         Call<Role> call = RetrofitClient.getInstance().getApi().deleteRole(role);
         call.enqueue(new Callback<Role>() {
             @Override
             public void onResponse(Call<Role> call, Response<Role> response) {
                 if(response.code()==200){
                     Toast.makeText(getApplicationContext(), "Delete Successful", Toast.LENGTH_LONG).show();
                 }else{
                     Toast.makeText(getApplicationContext(), "Delete Fail", Toast.LENGTH_LONG).show();
                 }
             }

             @Override
             public void onFailure(Call<Role> call, Throwable t) {
                 Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
             }
         });
    }

    private void getAllRoles(){
        Call<List<Role>> call = RetrofitClient.getInstance().getApi().findAllRoles();
        call.enqueue(new Callback<List<Role>>() {
            @Override
            public void onResponse(Call<List<Role>> call, Response<List<Role>> response) {
                List<Role> role = response.body();
                for (Role currentRole : role) {
                    if(currentRole.getRoleName().equals("null")){

                    }else{
                        roleList.add(currentRole);
                    }

                }

            }

            @Override
            public void onFailure(Call<List<Role>> call, Throwable t) {

            }
        });
    }
}
