package com.example.fyp.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fyp.Class.User;
import com.example.fyp.R;

import java.util.ArrayList;

public class AdminEmployeeUserAdapter extends ArrayAdapter<User> {
    private static final String TAG = "EmployeeListAdapter";
    private Context mContext;
    int mResource;
    public AdminEmployeeUserAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String firstName = getItem(position).getFirstName();
        String lastName = getItem(position).getLastName();
        String IC = getItem(position).getIC();
        String Contact = getItem(position).getContact();
        String Role = getItem(position).getRole();


        User user = new User(firstName,lastName,IC,Contact);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView first = (TextView)convertView.findViewById(R.id.tv_employeeFirstName);
        TextView last = (TextView)convertView.findViewById(R.id.tv_employeeLastName);
        TextView contact = (TextView)convertView.findViewById(R.id.tv_employeeContact);
        TextView ic = (TextView)convertView.findViewById(R.id.tv_employeeIc);
        TextView role = (TextView)convertView.findViewById(R.id.tv_employeeRoles);


        last.setText(lastName);
        first.setText(firstName);
        contact.setText(Contact);
        ic.setText(IC);
        role.setText(Role);

        return convertView;
    }
}

