package com.example.fyp.Orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fyp.Class.OrderCartSession;
import com.example.fyp.Class.Storage;
import com.example.fyp.R;

import java.util.ArrayList;

public class OrdersCartListAdapter extends ArrayAdapter<OrderCartSession> {
    private static final String TAG = "OrdersCartListAdapter";
    private Context mContext;
    int mResource;
    public OrdersCartListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<OrderCartSession> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String id = getItem(position).getProductId();
        String quantity = getItem(position).getOrderQuantity();

        Storage buyer = new Storage(id,quantity);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tv_productName = (TextView)convertView.findViewById(R.id.tv_productName);
        TextView tv_productQuantity = (TextView)convertView.findViewById(R.id.tv_productQuantity);

        tv_productName.append(id);
        tv_productQuantity.append(quantity);
        return convertView;
    }
}
