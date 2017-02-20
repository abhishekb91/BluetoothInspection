package com.wpi.walkermagnet_inspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wpi.walkermagnet_inspection.R;
import com.wpi.walkermagnet_inspection.activity.MainActivity;
import com.wpi.walkermagnet_inspection.data.model.Magnet;
import com.wpi.walkermagnet_inspection.misc.CustomBottomSheetDialog;

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
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listMagnetView = convertView;

        if (listMagnetView == null) {
            listMagnetView = LayoutInflater.from(getContext()).inflate(
                    R.layout.magnet_list, parent, false
            );
        }

        //Get current reference ingredient
        Magnet currentMagnet = getItem(position);

        //Disabling the ImageButton Focus event, in order to make the list selectable
        ImageButton btn = (ImageButton) listMagnetView.findViewById(R.id.magnet_option_btn);

        //Creating Reference of Bottom Sheet Dialog
        final CustomBottomSheetDialog bottomSheetDialog = new CustomBottomSheetDialog();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Setting the id of the selected magnet
                bottomSheetDialog.setControllerAttr(getItemId(position), mContext, position);

                //Showing the Bottom sheet
                bottomSheetDialog.show( ((MainActivity)mContext).getSupportFragmentManager(), "Custom Bottom Sheet");
            }
        });

        //Setting the magnet name
        TextView magnetName = (TextView) listMagnetView.findViewById(R.id.magnet_name);
        magnetName.setText(currentMagnet.getMagnetName());

        //Returning the Magnet list
        return listMagnetView;
    }
}
