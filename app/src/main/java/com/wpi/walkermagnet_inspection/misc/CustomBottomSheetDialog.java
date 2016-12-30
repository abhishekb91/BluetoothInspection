package com.wpi.walkermagnet_inspection.misc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpi.walkermagnet_inspection.R;

/**
 * Created by abhishek on 12/29/2016.
 */

public class CustomBottomSheetDialog extends BottomSheetDialogFragment {
    public static CustomBottomSheetDialog getInstance() {
        return new CustomBottomSheetDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.controller_bottomsheet, container, false);
    }
}
