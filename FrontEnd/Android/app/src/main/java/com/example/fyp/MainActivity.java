package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.BackEndServer.RetrofitClient;
import com.example.fyp.Class.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText useric,password;
    private TextView tv;
    private Button login,register;
    private CheckBox rmbMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // SharedPreferences sharedPreferences = getSharedPreferences("rememberMe", Context.MODE_PRIVATE);
        useric = (EditText)findViewById(R.id.loginIC);
        password =(EditText)findViewById(R.id.loginPassword);
        tv = (TextView)findViewById(R.id.textView2);
        login = (Button)findViewById(R.id.userLogIn);
        register =(Button)findViewById(R.id.Register);
        rmbMe = (CheckBox)findViewById(R.id.checkBox);

//        if (sharedPreferences.getString("ic", null)!=null && sharedPreferences.getString("password", null) !=null) {
//            useric.setText(sharedPreferences.getString("ic",null));
//            password.setText(sharedPreferences.getString("password", null));
//        }
        login.setOnClickListener(e -> {
//            if(rmbMe.isChecked()) {
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("ic", useric.getText().toString());
//                editor.putString("password", password.getText().toString());
//                editor.commit();
//            } else {
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.clear();
//                editor.commit();
//            }
            //User user = new User("951219015471","1234");
            //User user = new User("940528014566","1234");
            User user = new User(useric.getText().toString(),password.getText().toString());
            login(user);
//            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
//            startActivity(intent);
        });
        register.setOnClickListener(e->{
            Intent intent = new Intent(MainActivity.this, Profile_Register.class);
            intent.putExtra("check","true");
            startActivity(intent);
            //getAllUsers();
        });
    }
    private void getAllUsers(){
        Call<List<User>> call = RetrofitClient.getInstance().getApi()
                .findAllUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(!response.isSuccessful()){
                    tv.setText("Code:"+response.code());
                    return;
                }
                List<User> user = response.body();
                for(User currentuser: user){
                    String content="";
                    content+="IC:"+currentuser.getIC()+"\n";
                    tv.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                tv.setText(t.getMessage());
            }
        });

    }

    private void login(User user){
        Call<User> call = RetrofitClient.getInstance().getApi().searchUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    tv.append("Invalid User IC and Password");
                }else{
                    //tv.append("valid");
                    User user = response.body();
                    Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                    intent.putExtra("userIc",user.getIC());
                    intent.putExtra("userRole", user.getRole());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
