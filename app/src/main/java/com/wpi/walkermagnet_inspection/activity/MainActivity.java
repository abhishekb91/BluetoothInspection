package com.wpi.walkermagnet_inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wpi.walkermagnet_inspection.R;
import com.wpi.walkermagnet_inspection.adapter.MagnetAdapter;
import com.wpi.walkermagnet_inspection.data.repo.MagnetRepo;
import com.wpi.walkermagnet_inspection.misc.CustomBottomSheetDialog;
import com.wpi.walkermagnet_inspection.misc.SessionManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Session Manager Class
    SessionManager session;

    ArrayList magnets;

    MagnetAdapter magnetAdapter;

    MagnetRepo magnetRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Session class instance
        session = new SessionManager(getApplicationContext());

        /**
         * Checking is user is logged in or not,
         * If user is not logged in redirect it to Login Screen, else continue
         * */
        Boolean isLogin = session.checkLogin();

        if (!isLogin) {

            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            // Closing all the Activities
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            startActivity(i);

            finish();
        }


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Creating Magnets repo object
        magnetRepo = new MagnetRepo();

        //Getting all magnet controller of a user
        magnets = magnetRepo.magnets(session.getUserId());

        //Getting the MagnetAdapter reference
        magnetAdapter = new MagnetAdapter(this, magnets);

        //Referencing the ListView
        ListView listView = (ListView) findViewById(R.id.magnets_list);

        //Setting Adapter to the view
        listView.setAdapter(magnetAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Function to remove a controller from the list
     *
     * @param controllerId id of the magnet controller
     * @param position     position of the controller on the list
     */
    public void deleteMagnetController(long controllerId, int position) {

        //Remove controller from the database
        Boolean isDelete = magnetRepo.remove(controllerId, session.getUserId());

        if (isDelete) {
            //Remove and update the list
            magnets.remove(position);
            magnetAdapter.notifyDataSetChanged();

            //Show notification to the user
            Toast.makeText(getApplicationContext(), "Magnet Controller successfully deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Error Occurred, Please try later", Toast.LENGTH_SHORT).show();
        }

    }
}
