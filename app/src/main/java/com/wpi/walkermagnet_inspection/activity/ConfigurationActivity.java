package com.wpi.walkermagnet_inspection.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.wpi.walkermagnet_inspection.R;
import com.wpi.walkermagnet_inspection.adapter.ConfigurationParameterAdapter;
import com.wpi.walkermagnet_inspection.data.model.Magnet;
import com.wpi.walkermagnet_inspection.data.repo.ConfigurationRepo;
import com.wpi.walkermagnet_inspection.data.repo.MagnetRepo;
import com.wpi.walkermagnet_inspection.data.repo.ParameterRepo;

import java.util.ArrayList;


public class ConfigurationActivity extends AppCompatActivity {

    private static String TAG = ConfigurationActivity.class.getSimpleName().toString();

    private long magnetControllerId;

    private ArrayList mSelectedParameters = new ArrayList();

    private Magnet mMagnetInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        //Getting the selected magnet controller id
        magnetControllerId = (long) getIntent().getSerializableExtra("magnetControllerId");

        //Creating Magnets Repo object
        ParameterRepo parameterRepo = new ParameterRepo();

        //Creating Magnets Repo object
        MagnetRepo magnetRepo = new MagnetRepo();

        //Getting Magnet info
        mMagnetInfo = magnetRepo.magnet(magnetControllerId);

        //Getting Selected parameter by user
        mSelectedParameters = parameterRepo.configuration_parameter(mMagnetInfo.getConfigurationId());

        //Creating Configuration Repo object
        final ConfigurationRepo configurationRepo = new ConfigurationRepo();

        //Getting all magnet controller of a user
        ArrayList parameters = parameterRepo.parameters();

        //Getting the MagnetAdapter reference
        ConfigurationParameterAdapter configurationParameterAdapter = new ConfigurationParameterAdapter(this, parameters, mSelectedParameters);

        //Referencing the ListView
        ListView listView = (ListView) findViewById(R.id.configuration_list);

        //Setting Adapter to the view
        listView.setAdapter(configurationParameterAdapter);

        //Save Button reference
        Button saveBtn = (Button) findViewById(R.id.save_configuration_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean isSaved = configurationRepo.saveMagnetConfiguration(mMagnetInfo.getConfigurationId(), mSelectedParameters);

                if (isSaved) {
                    Toast.makeText(ConfigurationActivity.this, getResources().getText(R.string.config_save_success), Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    Toast.makeText(ConfigurationActivity.this, getResources().getText(R.string.config_save_error), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * Saves the configuration id of the checked parameter
     *
     * @param parameterId id of the changed parameter
     */
    public void setCheckedConfiguration(Long parameterId) {

        if (mSelectedParameters.contains(parameterId)) {
            mSelectedParameters.remove(parameterId);
        } else {
            mSelectedParameters.add(parameterId);
        }

        return;
    }
}
