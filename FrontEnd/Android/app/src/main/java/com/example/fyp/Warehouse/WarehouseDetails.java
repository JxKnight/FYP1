package com.example.fyp.Warehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.BackEndServer.RetrofitClient;
import com.example.fyp.Class.Storage;
import com.example.fyp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WarehouseDetails extends AppCompatActivity {
    TextView reCorrectQuantityTV;
    EditText reCorrectQuantity;
    EditText productID,productName,productCategory,productQuantity;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_details);  DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.8));

        Intent data = getIntent();
        productID = (EditText)findViewById(R.id.productID);
        productName = (EditText)findViewById(R.id.productName);
        productCategory = (EditText)findViewById(R.id.productCategory);
        productQuantity = (EditText)findViewById(R.id.productQuantity);
        reCorrectQuantity = (EditText)findViewById(R.id.reCorrectQuantity);
        reCorrectQuantityTV = (TextView)findViewById(R.id.reCorrectQuantityTv);
        btn = (Button)findViewById(R.id.ReCorrect);
            productID.setText(data.getStringExtra("productId"));
            productID.setInputType(InputType.TYPE_NULL);
            productName.setText(data.getStringExtra("productName"));
            productName.setInputType(InputType.TYPE_NULL);
            productCategory.setText(data.getStringExtra("productCategory"));
            productCategory.setInputType(InputType.TYPE_NULL);
            productQuantity.setText(data.getStringExtra("productQuantity"));
            productQuantity.setInputType(InputType.TYPE_NULL);


        btn.setOnClickListener(e->{
                if(reCorrectQuantity.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please insert the correct quantity of the product",Toast.LENGTH_LONG).show();
                }else{
                    Storage storage = new Storage(productID.getText().toString(),reCorrectQuantity.getText().toString());
                    updateStorage(storage);
                }
        });
    }
    private void updateStorage(Storage storage){
        Call<Storage> call = RetrofitClient.getInstance().getApi().updateStorage(storage);
        call.enqueue(new Callback<Storage>() {
            @Override
            public void onResponse(Call<Storage> call, Response<Storage> response) {
                if(response.code()==200){
                    Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Update Fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Storage> call, Throwable t) {

            }
        });
    }

}
