package com.example.fyp.Buyers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.fyp.BackEndServer.RetrofitClient;
import com.example.fyp.Class.Buyer;
import com.example.fyp.Products.Products;
import com.example.fyp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyerList extends AppCompatActivity {
    ArrayList<Buyer> buyerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_list);
        SwipeMenuListView buyersList = (SwipeMenuListView) findViewById(R.id.buyerList);
        Intent data = getIntent();
        buyerList = new ArrayList<Buyer>();
        BuyerListAdapter buyerListAdapter = new BuyerListAdapter(this, R.layout.buyer_list, buyerList);
        buyersList.setAdapter(buyerListAdapter);
        getBuyerList();
        Button addBuyer = (Button)findViewById(R.id.addBuyer);
        Button refreshList = (Button)findViewById(R.id.refreshList);
        refreshList.setOnClickListener(e->{
            finish();
            startActivity(getIntent());
        });
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
        buyersList.setMenuCreator(creator);
        buyersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (data.getStringExtra("check").equals("admin")) {
                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(BuyerList.this);
                    View mView = getLayoutInflater().inflate(R.layout.dialog_buyerdetails, null);
                    EditText buyerNameET = (EditText) mView.findViewById(R.id.buyerName);
                    EditText buyerContactET = (EditText) mView.findViewById(R.id.buyerContact);
                    EditText buyerLocationET = (EditText) mView.findViewById(R.id.buyerLocation);
                    EditText buyerAddress1ET = (EditText) mView.findViewById(R.id.buyerAddress1);
                    EditText buyerAddress2ET = (EditText) mView.findViewById(R.id.buyerAddress2);
                    EditText buyerRateET = (EditText) mView.findViewById(R.id.buyerRate);
                    Button buyerCheck = (Button) mView.findViewById(R.id.buyerCheck);
                    Button checkBack = (Button) mView.findViewById(R.id.checkBack);
                    buyerNameET.setText(buyerList.get(i).getBuyerName());
                    buyerContactET.setText(buyerList.get(i).getBuyerContact());
                    buyerLocationET.setText(buyerList.get(i).getBuyerLocation());
                    buyerAddress1ET.setText(buyerList.get(i).getBuyerAddress1());
                    buyerAddress2ET.setText(buyerList.get(i).getBuyerAddress2());
                    buyerRateET.setText(buyerList.get(i).getBuyerRate());
                    mBuilder.setView(mView);
                    AlertDialog dialog = mBuilder.create();
                    dialog.show();
                    buyerCheck.setOnClickListener(e -> {
                        String x = "true";
                        Buyer buyer = new Buyer(buyerList.get(i).getBuyerId(), buyerNameET.getText().toString(), buyerContactET.getText().toString(), buyerLocationET.getText().toString(), buyerAddress1ET.getText().toString(), buyerAddress2ET.getText().toString(), buyerRateET.getText().toString(), x);
                        updateAndCheckBuyer(buyer);
                    });
                    checkBack.setOnClickListener(e -> {
                        dialog.dismiss();
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "You cant perform this action", Toast.LENGTH_LONG).show();
                }
            }
        });
        buyersList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        if (data.getStringExtra("check").equals("admin")) {
                            // Toast.makeText(getApplicationContext(), roleList.get(i).getRoleName(), Toast.LENGTH_LONG).show();
                            AlertDialog.Builder mBuilder = new AlertDialog.Builder(BuyerList.this);
                            View mView = getLayoutInflater().inflate(R.layout.dialog_delete, null);
                            TextView companyName = (TextView) mView.findViewById(R.id.companyName);
                            Button yes = (Button) mView.findViewById(R.id.deleteYes);
                            Button no = (Button) mView.findViewById(R.id.deleteNo);
                            no.setOnClickListener(a -> {
                                buyerList.clear();
                                finish();
                                startActivity(getIntent());
                            });
                            yes.setOnClickListener(e -> {
                                Buyer buyer = new Buyer(buyerList.get(position).getBuyerId(), buyerList.get(position).getBuyerName(), buyerList.get(position).getBuyerContact(), buyerList.get(position).getBuyerLocation());
                                deleteBuyer(buyer);
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
        addBuyer.setOnClickListener(e->{
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(BuyerList.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_buyerdetails, null);
                EditText buyerNameET = (EditText) mView.findViewById(R.id.buyerName);
                EditText buyerContactET = (EditText) mView.findViewById(R.id.buyerContact);
                EditText buyerLocationET = (EditText) mView.findViewById(R.id.buyerLocation);
                EditText buyerAddress1ET = (EditText) mView.findViewById(R.id.buyerAddress1);
                EditText buyerAddress2ET = (EditText) mView.findViewById(R.id.buyerAddress2);
                EditText buyerRateET = (EditText) mView.findViewById(R.id.buyerRate);
                Button buyerCheck = (Button) mView.findViewById(R.id.buyerCheck);
                Button checkBack = (Button) mView.findViewById(R.id.checkBack);
                buyerCheck.setText("Add");
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                buyerCheck.setOnClickListener(i -> {
                    String x = "true";
                    Buyer buyer = new Buyer(buyerNameET.getText().toString(), buyerContactET.getText().toString(), buyerLocationET.getText().toString(), buyerAddress1ET.getText().toString(), buyerAddress2ET.getText().toString(), data.getStringExtra("userIc"));
                    AddBuyer(buyer);
                    dialog.dismiss();
                });
                checkBack.setOnClickListener(i -> {
                    dialog.dismiss();
                });
        });
    }
    private void getBuyerList(){
        Call<List<Buyer>> call = RetrofitClient.getInstance().getApi().findAllBuyers();
        call.enqueue(new Callback<List<Buyer>>() {
            @Override
            public void onResponse(Call<List<Buyer>> call, Response<List<Buyer>> response) {
                List<Buyer> buyers = response.body();
                for (Buyer currentBuyer : buyers) {
                    buyerList.add(currentBuyer);
                }
            }

            @Override
            public void onFailure(Call<List<Buyer>> call, Throwable t) {

            }
        });
    }
    private void updateAndCheckBuyer(Buyer buyer){
        Call<Buyer> call = RetrofitClient.getInstance().getApi().updateBuyer(buyer);
        call.enqueue(new Callback<Buyer>() {
            @Override
            public void onResponse(Call<Buyer> call, Response<Buyer> response) {
                if(response.code()==200){
                    Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Update Fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Buyer> call, Throwable t) {

            }
        });
    }
    private void getBuyerUncheckedList(){
        Call<List<Buyer>> call = RetrofitClient.getInstance().getApi().findAllBuyersUncheck();
        call.enqueue(new Callback<List<Buyer>>() {
            @Override
            public void onResponse(Call<List<Buyer>> call, Response<List<Buyer>> response) {
                List<Buyer> buyers = response.body();
                for (Buyer currentBuyer : buyers) {
                    buyerList.add(currentBuyer);
                }
            }
            @Override
            public void onFailure(Call<List<Buyer>> call, Throwable t) {

            }
        });
    }
    private void deleteBuyer(Buyer buyer){
        Call<Buyer> call = RetrofitClient.getInstance().getApi().deleteBuyer(buyer);
        call.enqueue(new Callback<Buyer>() {
            @Override
            public void onResponse(Call<Buyer> call, Response<Buyer> response) {
                if(response.code()==200){
                    Toast.makeText(getApplicationContext(), "Delete Successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Delete Fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Buyer> call, Throwable t) {

            }
        });
    }
    private void AddBuyer(Buyer buyer){
        Call<Buyer> call  = RetrofitClient.getInstance().getApi().createBuyer(buyer);
        call.enqueue(new Callback<Buyer>() {
            @Override
            public void onResponse(Call<Buyer> call, Response<Buyer> response) {
                if(response.code()==200){
                    Toast.makeText(getApplicationContext(), "Create Successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Create Fail", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Buyer> call, Throwable t) {

            }
        });
    }
}
