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
import com.wpi.walkermagnet_inspection.data.model.ConfigParameters;

import java.util.ArrayList;

/**
 * Created by abhishek on 2/1/2017.
 */

public class ConfigurationParameterAdapter extends ArrayAdapter<ConfigParameters> {

    /**
     * Private attribute to save magnets
     */
    private ArrayList<ConfigParameters> mConfiguration;

    /**
     * Private attribute to save context
     */
    private Context mContext;

    public ConfigurationParameterAdapter(Activity context, ArrayList<ConfigParameters> configuration) {
        super(context, 0, configuration);

        mConfiguration = configuration;

        mContext = context;
    }

    @Override
    public long getItemId(int position) {
        return mConfiguration.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listParameterView = convertView;

        if (listParameterView == null) {
            listParameterView = LayoutInflater.from(getContext()).inflate(
                    R.layout.configuration_list, parent, false
            );
        }

        //Get current reference ingredient
        ConfigParameters currentParameter = getItem(position);


        //Setting the parameter name
        TextView parameterName = (TextView) listParameterView.findViewById(R.id.configuration_title);
        parameterName.setText(currentParameter.getParameterTitle());

        //Returning the Magnet list
        return listParameterView;
    }
}
