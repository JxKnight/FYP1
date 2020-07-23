package com.example.fyp.Warehouse;

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
import com.example.fyp.Class.Storage;
import com.example.fyp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Warehouse extends AppCompatActivity {

    private Button btn,addProduct;
    private TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);
        Intent data = getIntent();
        SwipeMenuListView warehouseList = (SwipeMenuListView) findViewById(R.id.warehouseList);
        test = (TextView)findViewById(R.id.test);
        btn = (Button)findViewById(R.id.refreshWarehouseList);
        ArrayList <Storage> productList = new ArrayList<>();
        Call<List<Storage>> call = RetrofitClient.getInstance().getApi().findAllStorage();
        call.enqueue(new Callback<List<Storage>>() {
            @Override
            public void onResponse(Call<List<Storage>> call, Response<List<Storage>> response) {

                List<Storage> storage = response.body();
                for (Storage currentStorage : storage) {
                   productList.add(currentStorage);
                }
            }
            @Override
            public void onFailure(Call<List<Storage>> call, Throwable t) {

            }
        });

        WarehouseListAdapter productListAdapter = new WarehouseListAdapter(this,R.layout.warehouse_product_list,productList);
        warehouseList.setAdapter(productListAdapter);
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
        warehouseList.setMenuCreator(creator);

        warehouseList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        if (data.getStringExtra("check").equals("admin")) {
                            // Toast.makeText(getApplicationContext(), roleList.get(i).getRoleName(), Toast.LENGTH_LONG).show();
                            AlertDialog.Builder mBuilder = new AlertDialog.Builder(Warehouse.this);
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
                                Storage product = new Storage(productList.get(position).getProductsId());
                                deleteWarehouse(product);
                                //Toast.makeText(getApplicationContext(),productList.get(index).getProductsId(),Toast.LENGTH_SHORT).show();
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
        warehouseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // String productName = String.valueOf(adapterView.getItemAtPosition(i));
                //String productName = warehouseList.getItemAtPosition(i).toString();
                String productName = productList.get(i).getProductsName();
                Intent intent = new Intent(Warehouse.this, WarehouseDetails.class);
                intent.putExtra("userIc",data.getStringExtra("userIc"));
                intent.putExtra("productId",productList.get(i).getProductsId());
                intent.putExtra("productName",productList.get(i).getProductsName());
                intent.putExtra("productCategory",productList.get(i).getProductsCategory());
                intent.putExtra("productQuantity",productList.get(i).getProductsQuantity());
                startActivity(intent);
            }
        });
        btn.setOnClickListener(e->{
            productList.clear();
            finish();
            startActivity(getIntent());
        });
    }
    private void deleteWarehouse(Storage product){
        Call<Storage> call = RetrofitClient.getInstance().getApi().deleteWarehouse(product);
        call.enqueue(new Callback<Storage>() {
            @Override
            public void onResponse(Call<Storage> call, Response<Storage> response) {
                if(response.code()==200){
                    Toast.makeText(getApplicationContext(), "Delete Successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Delete Fail", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Storage> call, Throwable t) {

            }
        });
    }
}
