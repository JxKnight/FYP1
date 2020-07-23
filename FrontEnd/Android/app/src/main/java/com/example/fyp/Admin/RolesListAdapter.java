package com.example.fyp.Admin;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fyp.Class.Role;
import com.example.fyp.Class.Storage;
import com.example.fyp.R;

import java.util.ArrayList;
import java.util.List;

public class RolesListAdapter extends ArrayAdapter<Role> {
    private static final String TAG = "RolesListAdapter";
    private Context mContext;
    int mResource;

    public RolesListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Role> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String Name = getItem(position).getRoleName();
        String Warehouse = getItem(position).getWarehouse();
        String Orders = getItem(position).getOrders();
        String Customers = getItem(position).getCustomers();
        String Reports = getItem(position).getReports();
        String Tasks = getItem(position).getTasks();

        Role role = new Role(Name,Warehouse,Orders,Customers,Reports,Tasks);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView name = (TextView)convertView.findViewById(R.id.tv_roleName);
        TextView warehouse = (TextView)convertView.findViewById(R.id.tv_roleWarehouse);
        TextView order = (TextView)convertView.findViewById(R.id.tv_roleOrder);
        TextView customer = (TextView)convertView.findViewById(R.id.tv_roleCustomer);
        TextView report = (TextView)convertView.findViewById(R.id.tv_roleReport);
        TextView task = (TextView)convertView.findViewById(R.id.tv_roleTask);

        name.append(Name);
        if(Warehouse.equals("0")){
            warehouse.setText("");
        }
        if(Orders.equals("0")){
            order.setText("");
        }
        if(Customers.equals("0")){
            customer.setText("");
        }
        if(Reports.equals("0")){
            report.setText("");
        }
        if(Tasks.equals("0")){
            task.setText("");
        }

        return convertView;
    }
}
