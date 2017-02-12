package com.wpi.walkermagnet_inspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.wpi.walkermagnet_inspection.R;
import com.wpi.walkermagnet_inspection.activity.ConfigurationActivity;
import com.wpi.walkermagnet_inspection.data.model.Parameters;

import java.util.ArrayList;

/**
 * Created by abhishek on 2/1/2017.
 */

public class ConfigurationParameterAdapter extends ArrayAdapter<Parameters> {

    /**
     * Private attribute to save magnets
     */
    private ArrayList<Parameters> mConfiguration;

    /**
     * Private attribute to save context
     */
    private Context mContext;

    private ArrayList mSelectedParameters;

    public ConfigurationParameterAdapter(Activity context, ArrayList<Parameters> configuration, ArrayList selectedParameters) {
        super(context, 0, configuration);

        mConfiguration = configuration;

        mSelectedParameters = selectedParameters;

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
        Parameters currentParameter = getItem(position);

        //Referencing Parameter Title
        TextView parameterName = (TextView) listParameterView.findViewById(R.id.configuration_title);

        //Setting the parameter name
        parameterName.setText(currentParameter.getParameterTitle());

        //Referencing Parameter Switch
        Switch configSwitch = (Switch) listParameterView.findViewById(R.id.configuration_value);

        //If parameter was already selected check it, else ignore
        if (mSelectedParameters.contains(getItemId(position))) {
            configSwitch.setChecked(true);
        }

        //Listening switch to check if user has selected a parameter or not
        configSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Save the id of the parameter in the parent activity
                if (mContext instanceof ConfigurationActivity) {
                    ((ConfigurationActivity) mContext).setCheckedConfiguration(getItemId(position));
                }
            }
        });

        //Returning the Magnet list
        return listParameterView;
    }
}
