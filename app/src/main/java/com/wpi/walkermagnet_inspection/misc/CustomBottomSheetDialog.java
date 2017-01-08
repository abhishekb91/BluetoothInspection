package com.wpi.walkermagnet_inspection.misc;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wpi.walkermagnet_inspection.R;
import com.wpi.walkermagnet_inspection.activity.MainActivity;

import static com.wpi.walkermagnet_inspection.R.id.delete_magnet_row;

/**
 * Created by abhishek on 12/29/2016.
 */

public class CustomBottomSheetDialog extends BottomSheetDialogFragment {

    /**
     * Property to store magnet controller id
     */
    private long mMagnetControllerId;

    public static CustomBottomSheetDialog getInstance() {
        return new CustomBottomSheetDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Referencing View
        View v = inflater.inflate(R.layout.controller_bottomsheet, container, false);

        //Referencing Magnet name TextView
        TextView magnetName = (TextView) v.findViewById(R.id.magnet_controller_name);
        magnetName.setText("Magnet Controller " + mMagnetControllerId);

        //Referencing Delete Row
        LinearLayout deleteMagnetRow = (LinearLayout) v.findViewById(delete_magnet_row);
        deleteMagnetRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.app_name)
                        .setMessage(R.string.delete_magnet_alert)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getActivity(), "Magnet with id = " + mMagnetControllerId + " successfully deleted", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
        return v;
    }

    /**
     * This function is used to set the magnet id for which the menu options are shown to the user
     *
     * @param id magnet controller id
     */
    public void setMagnetControllerId(long id) {
        mMagnetControllerId = id;
        return;
    }
}
