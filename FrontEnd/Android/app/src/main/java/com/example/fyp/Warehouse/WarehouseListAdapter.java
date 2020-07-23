package com.example.fyp.Warehouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fyp.Class.Storage;
import com.example.fyp.R;

import java.util.ArrayList;
import java.util.List;

public class WarehouseListAdapter extends ArrayAdapter<Storage> {
    private static final String TAG = "ProductListAdapter";
    private Context mContext;
    int mResource;

    public WarehouseListAdapter(@NonNull Context context, int resource, @NonNull List<Storage> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String Name = getItem(position).getProductsName();
        String Quantity = getItem(position).getProductsQuantity();
        String Category = getItem(position).getProductsCategory();

        Storage storage = new Storage(Name,Category,Quantity);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView name = (TextView)convertView.findViewById(R.id.tv_productName);
        TextView category = (TextView)convertView.findViewById(R.id.tv_productCategory);
        TextView quantity = (TextView)convertView.findViewById(R.id.tv_productQuantity);

        name.setText(Name);
        category.setText(Category);
        quantity.setText(Quantity);

        return convertView;
    }
}
