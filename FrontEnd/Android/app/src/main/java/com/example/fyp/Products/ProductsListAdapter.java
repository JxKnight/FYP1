package com.example.fyp.Products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fyp.Class.Product;
import com.example.fyp.Class.Storage;
import com.example.fyp.R;

import java.util.ArrayList;
import java.util.List;

public class ProductsListAdapter extends ArrayAdapter<Product> {
    private static final String TAG = "ProductsListAdapter";
    private Context mContext;
    int mResource;
    public ProductsListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Product> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String category = getItem(position).getProductsCategory();
        String name = getItem(position).getProductsName();

        Product product = new Product(name,category);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tv_productName = (TextView)convertView.findViewById(R.id.productName);
        TextView tv_productCategory = (TextView)convertView.findViewById(R.id.productCategory);

        tv_productName.append(name);
        tv_productCategory.append(category);
        return convertView;
    }
}
