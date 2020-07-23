package com.example.fyp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fyp.BackEndServer.RetrofitClient;
import com.example.fyp.Class.User;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Register extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private String check="";
    private EditText FNET,LNET,CET,AET,AET2,PET,RPET,IC;
    private TextView STV,BTV,RPTV,TotalSalartyTv,TotalSalary;
    private Button btn,pickDate,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__register);
        Intent data = getIntent();
        FNET = (EditText) findViewById(R.id.FirstNameET);
        LNET = (EditText) findViewById(R.id.LastNameET);
        CET = (EditText) findViewById(R.id.ContactET);
        IC = (EditText) findViewById(R.id.userICET);
        AET = (EditText) findViewById(R.id.AddressET1);
        AET2 = (EditText)findViewById(R.id.AddressET2);
        PET = (EditText) findViewById(R.id.PasswordET);
        RPET = (EditText) findViewById(R.id.RePasswordET);

        BTV = (TextView) findViewById(R.id.BirthDateTextView);
        STV = (TextView) findViewById(R.id.TotalSalary);
        RPTV = (TextView) findViewById(R.id.RePasswordTV);
        back = (Button)findViewById(R.id.backButton);
        TotalSalartyTv = (TextView)findViewById(R.id.TotalSalaryTV);
        TotalSalary = (TextView)findViewById(R.id.TotalSalary);

        check=getIntent().getStringExtra("check");
        pickDate = (Button) findViewById(R.id.PickDate);
        btn = (Button)findViewById(R.id.button);

        if (check.equals("true")) {
            btn.setText("Register");
            TotalSalary.setText("");
            TotalSalartyTv.setText("");
        } else {
            User user = new User(data.getStringExtra("userIc"));
            viewProfile(user);
            RPTV.setText("Confirm Password");
        }

        back.setOnClickListener(e->{
            finish();
        });
        btn.setOnClickListener(e->{
            if(check.equals("true")){
                if(PET.getText().toString().equals(RPET.getText().toString())){
                    if(FNET.getText().toString().isEmpty()||LNET.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "FirstName and LastName cannot be empty", Toast.LENGTH_LONG).show();
                    }else{
                        User user = new User(FNET.getText().toString(),LNET.getText().toString(),PET.getText().toString(),IC.getText().toString(),CET.getText().toString(),BTV.getText().toString(),AET.getText().toString(),AET2.getText().toString());
                        registerProfile(user);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Register Fail, Password Incorrect", Toast.LENGTH_LONG).show();
                }
            }else{
                if(PET.getText().toString().equals(RPET.getText().toString())) {
                    User user = new User(FNET.getText().toString(),LNET.getText().toString(),PET.getText().toString(),IC.getText().toString(),CET.getText().toString(),BTV.getText().toString(),AET.getText().toString(),AET2.getText().toString());
                    updateProfile(user);
                }
            }
        });

        pickDate.setOnClickListener(e->{
            showDatePickerDialog();
        });
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Integer m = month+1;
        String date = day+"/"+m+"/"+year;
        BTV.setText(date);
    }

    public void viewProfile(User user){
        Call<User> call = RetrofitClient.getInstance().getApi().searchCurrentUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_LONG).show();
                    return;
                }
                User postResponse = response.body();
                FNET.setText(postResponse.getFirstName());
                FNET.setInputType(InputType.TYPE_NULL);
                LNET.setText(postResponse.getLastName());
                LNET.setInputType(InputType.TYPE_NULL);
                IC.setText(postResponse.getIC());
                IC.setInputType(InputType.TYPE_NULL);
                CET.setText(postResponse.getContact());
                AET.setText(postResponse.getAddress1());
                AET2.setText(postResponse.getAddress2());
                BTV.setText(postResponse.getBirthDate());
                BTV.setInputType(InputType.TYPE_NULL);
                PET.setText(postResponse.getPassword());
                PET.setInputType(InputType.TYPE_NULL);
                PET.setTransformationMethod((PasswordTransformationMethod.getInstance()));
                STV.setText(postResponse.getSalary().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void registerProfile(User user){
        Call<User> call = RetrofitClient.getInstance().getApi().createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Register UnSuccessful, \nDuplicate Name Exist", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void updateProfile(User user){
        Call<User> call = RetrofitClient.getInstance().getApi().updateUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code()==200){
                    Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Update Fail", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
