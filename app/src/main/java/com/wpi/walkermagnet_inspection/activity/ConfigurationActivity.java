package com.wpi.walkermagnet_inspection.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.wpi.walkermagnet_inspection.R;
import com.wpi.walkermagnet_inspection.adapter.ConfigurationParameterAdapter;
import com.wpi.walkermagnet_inspection.data.repo.ConfigParameterRepo;

import java.util.ArrayList;

public class ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        //Creating Magnets repo object
        ConfigParameterRepo parameterRepo = new ConfigParameterRepo();

        //Getting all magnet controller of a user
        ArrayList parameters = parameterRepo.parameters();

        //Getting the MagnetAdapter reference
        ConfigurationParameterAdapter configurationParameterAdapter = new ConfigurationParameterAdapter(this, parameters);

        //Referencing the ListView
        ListView listView = (ListView) findViewById(R.id.configuration_list);

        //Setting Adapter to the view
        listView.setAdapter(configurationParameterAdapter);
    }
}
