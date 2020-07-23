package com.example.fyp.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.fyp.BackEndServer.RetrofitClient;
import com.example.fyp.Buyers.BuyerList;
import com.example.fyp.Class.Buyer;
import com.example.fyp.Class.Product;
import com.example.fyp.MenuActivity;
import com.example.fyp.Products.ProductsListAdapter;
import com.example.fyp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminProduct extends AppCompatActivity {
    ArrayList<Product> productList;
    String userIc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);
        Intent data = getIntent();
        productList = new ArrayList<>();
        userIc = data.getStringExtra("userIc");
        SwipeMenuListView list = (SwipeMenuListView)findViewById(R.id.productsList);
        Button addProduct = (Button)findViewById(R.id.addProduct);
        getProducts();
        Button refreshList = (Button)findViewById(R.id.refreshList);
        refreshList.setOnClickListener(e->{
            finish();
            startActivity(getIntent());
        });
        ProductsListAdapter productsListAdapter = new ProductsListAdapter(this, R.layout.products_list, productList);
        list.setAdapter(productsListAdapter);
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
        list.setMenuCreator(creator);
        list.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        if (data.getStringExtra("check").equals("admin")) {
                            // Toast.makeText(getApplicationContext(), roleList.get(i).getRoleName(), Toast.LENGTH_LONG).show();
                            AlertDialog.Builder mBuilder = new AlertDialog.Builder(AdminProduct.this);
                            View mView = getLayoutInflater().inflate(R.layout.dialog_delete, null);
                            TextView companyName = (TextView) mView.findViewById(R.id.companyName);
                            Button yes = (Button) mView.findViewById(R.id.deleteYes);
                            Button no = (Button) mView.findViewById(R.id.deleteNo);
                            no.setOnClickListener(a -> {
                                productList.clear();
                                finish();
                                startActivity(getIntent());
                            });
                            yes.setOnClickListener(e -> {
                                Product product = new Product(productList.get(position).getProductsId());
                                deleteProduct(product);
                            });
                            mBuilder.setView(mView);
                            AlertDialog dialog = mBuilder.create();
                            dialog.show();
                        } else {
                            Toast.makeText(getApplicationContext(), "You cant perform this action", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        addProduct.setOnClickListener(e->{
            Intent intent = new Intent(AdminProduct.this, AdminAddProduct.class);
            intent.putExtra("userIc",userIc);
            startActivity(intent);
        });
    }
    private void getProducts(){
        Call<List<Product>> call = RetrofitClient.getInstance().getApi().findAllProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.code(),Toast.LENGTH_LONG).show();
                    return;
                }else {
                    List<Product> products = response.body();
                    for (Product currentProduct : products) {
                        productList.add(currentProduct);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    private void deleteProduct(Product product){
        Call<Product> call = RetrofitClient.getInstance().getApi().deleteProduct(product);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if(response.code()==200){
                    Toast.makeText(getApplicationContext(), "Delete Successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Delete Fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });

    }
}
