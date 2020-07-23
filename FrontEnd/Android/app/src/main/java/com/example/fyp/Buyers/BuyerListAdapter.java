package com.example.fyp.Buyers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fyp.Class.Buyer;
import com.example.fyp.Class.Role;
import com.example.fyp.R;

import java.util.ArrayList;
import java.util.List;

public class BuyerListAdapter extends ArrayAdapter<Buyer> {
    private static final String TAG = "BuyerListAdapter";
    private Context mContext;
    int mResource;
    public BuyerListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Buyer> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String Id = getItem(position).getBuyerId();
        String Name = getItem(position).getBuyerName();
        String Contact = getItem(position).getBuyerContact();
        String Location = getItem(position).getBuyerLocation();
        String Check = getItem(position).getUserCheck();


        Buyer buyer = new Buyer(Id,Name,Contact,Location,Check);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tv_buyerName = (TextView)convertView.findViewById(R.id.tv_buyerName);
        TextView tv_buyerContact = (TextView)convertView.findViewById(R.id.tv_buyerContact);
        TextView tv_buyerLocation = (TextView)convertView.findViewById(R.id.tv_buyerLocation);
        TextView tv_buyerCheck = (TextView)convertView.findViewById(R.id.tv_buyerCheck);

        tv_buyerName.append(Name);
        tv_buyerContact.append(Contact);
        tv_buyerLocation.append(Location);
        if(Check.equals("true")){
            tv_buyerCheck.setText("Check");
        }else{
            tv_buyerCheck.setText("Uncheck");
        }

        return convertView;
    }
}
