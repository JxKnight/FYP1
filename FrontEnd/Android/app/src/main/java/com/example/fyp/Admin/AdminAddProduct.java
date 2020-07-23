package com.example.fyp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fyp.BackEndServer.RetrofitClient;
import com.example.fyp.Class.Product;
import com.example.fyp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAddProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_product);
        EditText id = (EditText)findViewById(R.id.createProductID);
        EditText name = (EditText)findViewById(R.id.createProductName);
        EditText Category = (EditText)findViewById(R.id.createProductCategory);
        EditText Packing = (EditText)findViewById(R.id.createProductPacking);
        EditText Quantity = (EditText)findViewById(R.id.createProductQuantity);
        EditText Price = (EditText)findViewById(R.id.createProductPrice);
        EditText description = (EditText)findViewById(R.id.createProductDescription);

        Button addProduct =(Button)findViewById(R.id.addProduct);

        addProduct.setOnClickListener(e->{
            if(id.getText().toString().isEmpty()||name.getText().toString().isEmpty()||description.getText().toString().isEmpty()||Packing.getText().toString().isEmpty()||Quantity.getText().toString().isEmpty()||Price.getText().toString().isEmpty()||Category.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Please fill in all!", Toast.LENGTH_LONG).show();
            }else {
                Product product = new Product(id.getText().toString(), name.getText().toString(), description.getText().toString(), Packing.getText().toString(), Quantity.getText().toString(), Price.getText().toString(), Category.getText().toString());

                Call<Product> call = RetrofitClient.getInstance().getApi().createProduct(product);
                call.enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Create Product Fail", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Create Product Successful", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Fail to connect to server", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
