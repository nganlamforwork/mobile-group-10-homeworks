package com.example.hw03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityReceiver extends Activity {

    private TextView receiverUsername;
    private TextView receiverPassword;
    private TextView receiverBirthday;
    private TextView receiverGender;
    private TextView receiverHobbies;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        receiverUsername = findViewById(R.id.receiverUsername);
        receiverPassword = findViewById(R.id.receiverPassword);
        receiverBirthday = findViewById(R.id.receiverBirthday);
        receiverGender = findViewById(R.id.receiverGender);
        receiverHobbies = findViewById(R.id.receiverHobbies);

        Intent myCallerIntent = getIntent();
        Bundle myBundle = myCallerIntent.getExtras();

        String username = myBundle.getString("username");
        String password = myBundle.getString("password");
        String birthday = myBundle.getString("birthday");
        String gender = myBundle.getString("gender");
        String hobbies = myBundle.getString("hobbies");

        receiverUsername.setText(username);
        receiverPassword.setText(password);
        receiverBirthday.setText(birthday);
        receiverGender.setText(gender);
        receiverHobbies.setText(hobbies);

        // Put the data you want to send back
        myCallerIntent.putExtra("username", username);
        myCallerIntent.putExtra("password", password);
        myCallerIntent.putExtra("birthday", birthday);
        myCallerIntent.putExtra("gender", gender);
        myCallerIntent.putExtra("hobbies", hobbies);
        // Set the result with the Intent and the result code
        setResult(Activity.RESULT_OK, myCallerIntent);

        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });
    }
}