package com.wpi.walkermagnet_inspection.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wpi.walkermagnet_inspection.R;
import com.wpi.walkermagnet_inspection.data.model.User;
import com.wpi.walkermagnet_inspection.data.repo.UserRepo;
import com.wpi.walkermagnet_inspection.misc.SessionManager;

public class LoginActivity extends AppCompatActivity {

    private Button mSubmitBtn;

    private EditText mUserName, mUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializing User Name
        mUserName = (EditText) findViewById(R.id.user_name);

        //Initializing  User Email field
        mUserEmail = (EditText) findViewById(R.id.user_email);

        //Initializing  Submit Button on the login screen
        mSubmitBtn = (Button) findViewById(R.id.submit_btn);

        //Listening for click event
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Saving user information
                Boolean result = saveUserInfo();

                if(result) {
                    //User information was saved successfully, redirect to home activity

                    // Staring HomeActivity
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);

                    // Closing all the Activities
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(i);

                    finish();
                } else {
                    //Display error message
                    Toast.makeText(getApplication(), getString(R.string.error_validation_error), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * This function saves the user information into the application
     */
    private Boolean saveUserInfo() {
        if (validateFields()) {
            String userName = mUserName.getText().toString();
            String userEmail = mUserEmail.getText().toString();

            //Creating user object
            User userDetail = new User();

            //Storing user details in the object
            userDetail.setUserName(userName);
            userDetail.setmUserEmail(userEmail);

            UserRepo newUser = new UserRepo();
            long userId = newUser.insertUserInfo(userDetail);

            // Session class instance
            SessionManager session = new SessionManager(getApplicationContext());

            session.createLoginSession(userId);

            return true;
        } else {
            return false;
        }
    }

    /**
     * This function is used to validate the user input fields
     *
     * @return true if users has entered all the fields, else returns false
     */
    private Boolean validateFields() {
        // TODO: 1/17/2017 : Write validation code

        return true;
    }
}
