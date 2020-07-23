package com.example.fyp.Products;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.fyp.BackEndServer.RetrofitClient;
import com.example.fyp.Class.Buyer;
import com.example.fyp.Class.Order;
import com.example.fyp.Class.OrderCartSession;
import com.example.fyp.Class.Product;
import com.example.fyp.Orders.OrdersCartListAdapter;
import com.example.fyp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Products extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    ArrayList<Product> productList;
    ArrayList<OrderCartSession> orderList;
    ArrayList<String> buyerList;
    Button cartList;
    String userIc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Intent data = getIntent();
        productList = new ArrayList<>();
        orderList = new ArrayList<>();
        buyerList = new ArrayList<>();
        userIc = data.getStringExtra("userIc");
        SwipeMenuListView list = (SwipeMenuListView)findViewById(R.id.productsList);
        Button refreshList = (Button)findViewById(R.id.refreshList);
        refreshList.setOnClickListener(e->{
            finish();
            startActivity(getIntent());
        });
        cartList = (Button)findViewById(R.id.cartList);
        getProducts();
        ProductsListAdapter productsListAdapter = new ProductsListAdapter(this, R.layout.products_list, productList);
        list.setAdapter(productsListAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String productName = warehouseList.getItemAtPosition(i).toString();

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Products.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_productdetails, null);
                TextView productID = (TextView)mView.findViewById(R.id.productID);
                TextView  productName = (TextView)mView.findViewById(R.id.productName);
                TextView productCategory = (TextView)mView.findViewById(R.id.productCategory);
                TextView Packing = (TextView)mView.findViewById(R.id.Packing);
                TextView productPrice = (TextView)mView.findViewById(R.id.productPrice);
                Button  orderCart = (Button)mView.findViewById(R.id.orderCart);
                EditText orderQuantity = (EditText)mView.findViewById(R.id.orderQuantity);
                productID.setText(productList.get(i).getProductsId());
                productName.setText(productList.get(i).getProductsName());
                productCategory.setText(productList.get(i).getProductsCategory());
                Packing.setText(productList.get(i).getProductsPack());
                productPrice.setText(productList.get(i).getProductsPrice());

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                orderCart.setOnClickListener(a -> {
                    OrderCartSession order = new OrderCartSession(productList.get(i).getProductsId(),productList.get(i).getProductsName(),orderQuantity.getText().toString());
                    orderList.add(order);
                    dialog.dismiss();
                });
            }
        });

        cartList.setOnClickListener(e->{
            buyerList.clear();
            buyerList.add("Select Buyer");
            getBuyer();
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(Products.this);
            View mView = getLayoutInflater().inflate(R.layout.order_carts, null);
            Spinner buyerSpinner = (Spinner)mView.findViewById(R.id.buyerSpinner);
            Button selectBuyer = (Button)mView.findViewById(R.id.selectBuyer);
            Button Order = (Button)mView.findViewById(R.id.Order);
            SwipeMenuListView ordersList = (SwipeMenuListView)mView.findViewById(R.id.ordersList);
            TextView buyerName = (TextView)mView.findViewById(R.id.buyerCartName);
            EditText orderDescription = (EditText)mView.findViewById(R.id.orderDescription);
            OrdersCartListAdapter buyerListAdapter = new OrdersCartListAdapter(Products.this, R.layout.order_cart_list, orderList);
            ordersList.setAdapter(buyerListAdapter);
            ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,buyerList);
            locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            buyerSpinner.setAdapter(locationAdapter);
            buyerSpinner.setOnItemSelectedListener(this);
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
            selectBuyer.setOnClickListener(o->{
                String x = buyerSpinner.getSelectedItem().toString();
                buyerName.setText(x);
            });

            ordersList.setMenuCreator(creator);
            mBuilder.setView(mView);
            AlertDialog dialog = mBuilder.create();
            dialog.show();
            ordersList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                    switch (index) {
                        case 0:
                            orderList.remove(position);
                            dialog.dismiss();
                            break;
                    }
                    // false : close the menu; true : not close the menu
                    return false;
                }
            });
            Order.setOnClickListener(z->{
                Date c = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = df.format(c);
                //Toast.makeText(getApplicationContext(),formattedDate,Toast.LENGTH_LONG).show();
                String productsId="",productsQuantity="";
                for(int i=0;i<orderList.size();i++){
                    productsId = productsId + orderList.get(i).getProductName()+"/";
                    productsQuantity = productsQuantity + orderList.get(i).getOrderQuantity()+"/";
                }
                Order order = new Order(orderDescription.getText().toString(),formattedDate,buyerSpinner.getSelectedItem().toString(),productsId,productsQuantity,userIc);
                createOrder(order);
                dialog.dismiss();
            });
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

    private void getBuyer(){
        Call<List<Buyer>> call = RetrofitClient.getInstance().getApi().findAllBuyers();
        call.enqueue(new Callback<List<Buyer>>() {
            @Override
            public void onResponse(Call<List<Buyer>> call, Response<List<Buyer>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),response.code(),Toast.LENGTH_LONG).show();
                    return;
                }else {
                    List<Buyer> buyer = response.body();
                    for (Buyer currentBuyer : buyer) {
                        buyerList.add(currentBuyer.getBuyerName());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Buyer>> call, Throwable t) {
                // buyerName.setText(t.getMessage());
            }
        });
    }

    private void createOrder(Order order){
        Call<Order> call = RetrofitClient.getInstance().getApi().createOrder(order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.code()==200){
                    Toast.makeText(getApplicationContext(), "Create Order Successful", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Create Order Fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

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
