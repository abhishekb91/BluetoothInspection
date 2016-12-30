package com.wpi.walkermagnet_inspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wpi.walkermagnet_inspection.R;
import com.wpi.walkermagnet_inspection.data.model.Magnet;

import java.util.ArrayList;

/**
 * Created by abhishek on 12/29/2016.
 */

public class MagnetAdapter extends ArrayAdapter<Magnet>{

    /**
     * Private attribute to save magnets
     */
    private ArrayList<Magnet> mMagnet;

    /**
     * Private attribute to save context
     */
    private Context mContext;

    public MagnetAdapter(Activity context, ArrayList<Magnet> magnet) {
        super(context, 0 , magnet);

        mMagnet = magnet;

        mContext = context;
    }

    @Override
    public long getItemId(int position) {
        return mMagnet.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listMagnetView = convertView;

        if (listMagnetView == null) {
            listMagnetView = LayoutInflater.from(getContext()).inflate(
                    R.layout.magnet_list, parent, false
            );
        }

        //Get current reference ingredient
        Magnet currentMagnet = getItem(position);

        //Setting the magnet name
        TextView magnetName = (TextView) listMagnetView.findViewById(R.id.magnet_name);
        magnetName.setText(currentMagnet.getMagnetName());

        //Returning the Magnet list
        return listMagnetView;
    }
}
