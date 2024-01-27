package com.example.hw03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpActivity extends Activity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText retypeEditText;
    private EditText birthdayEditText;
    private CheckBox tennisCheckBox;
    private CheckBox futbalCheckBox;
    private CheckBox othersCheckBox;
    private RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        RadioButton maleRadioButton = (RadioButton) findViewById(R.id.male);
        maleRadioButton.setChecked(true);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        retypeEditText = findViewById(R.id.retype);
        birthdayEditText = findViewById(R.id.birthday);

        tennisCheckBox = findViewById(R.id.tennis);
        futbalCheckBox = findViewById(R.id.futbal);
        othersCheckBox = findViewById(R.id.others);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);

        Button resetButton = findViewById(R.id.btnReset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameEditText.setText("");
                passwordEditText.setText("");
                retypeEditText.setText("");
                birthdayEditText.setText("");

                genderRadioGroup.clearCheck();

                tennisCheckBox.setChecked(false);
                futbalCheckBox.setChecked(false);
                othersCheckBox.setChecked(false);
                RadioButton maleRadioButton = (RadioButton) findViewById(R.id.male);
                maleRadioButton.setChecked(true);

                showToast("Fields reset.");
            }
        });

        Button signUpButton = findViewById(R.id.btnSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String retype = retypeEditText.getText().toString();
                String birthday = birthdayEditText.getText().toString();

                if (username.equals("")) {
                    showToast("Usename!!!");
                }else if (!password.equals(retype)) {
                    showToast("Passwords do not match. Please re-enter.");
                }
                else if (!isValidDate(birthday)) {
                    showToast("Please check your birthday format.");
                } else {
                showToast("Sign-Up successful!");
                openActivityReceiver();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishAffinity();
        System.exit(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        finishAffinity();
        System.exit(0);

    }

    private boolean isValidDate(String inputDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            Date date = dateFormat.parse(inputDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private String getHobbiesString() {
        StringBuilder response = new StringBuilder();

        if(tennisCheckBox.isChecked()){
            response.append(tennisCheckBox.getText().toString());
            response.append(", ");
        }
        if(futbalCheckBox.isChecked()){
            response.append(futbalCheckBox.getText().toString());
            response.append(", ");
        }
        if(othersCheckBox.isChecked()){
            response.append(othersCheckBox.getText().toString());
            response.append(", ");
        }

        // remove comma
        if (response.length() > 0) {
            response.setLength(response.length() - 2);
        }

        return response.toString();
    }

    private String getGender(){
        int id = genderRadioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(id);


        System.out.println("----------------------------------------------------");
        System.out.println(radioButton.getText().toString());
        return radioButton.getText().toString();
    }

    private void openActivityReceiver() {
        Intent intent = new Intent(this, ActivityReceiver.class);
        Bundle myBundle = new Bundle();
        myBundle.putString("username", usernameEditText.getText().toString());
        myBundle.putString("password", passwordEditText.getText().toString());
        myBundle.putString("birthday", birthdayEditText.getText().toString());
        myBundle.putString("gender", this.getGender());
        myBundle.putString("hobbies", this.getHobbiesString());
        intent.putExtras(myBundle);
        startActivityForResult(intent, 1122);
    }
}
