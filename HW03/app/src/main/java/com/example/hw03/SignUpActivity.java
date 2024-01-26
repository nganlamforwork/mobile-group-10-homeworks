package com.example.hw03;

import android.app.Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        retypeEditText = findViewById(R.id.retype);
        birthdayEditText = findViewById(R.id.birthday);

        tennisCheckBox = findViewById(R.id.tennis);
        futbalCheckBox = findViewById(R.id.futbal);
        othersCheckBox = findViewById(R.id.others);

        Button resetButton = findViewById(R.id.btnReset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameEditText.setText("");
                passwordEditText.setText("");
                retypeEditText.setText("");
                birthdayEditText.setText("");

                RadioGroup genderRadioGroup = findViewById(R.id.genderRadioGroup);
                genderRadioGroup.clearCheck();

                tennisCheckBox.setChecked(false);
                futbalCheckBox.setChecked(false);
                othersCheckBox.setChecked(false);

                showToast("Fields reset.");
            }
        });

        Button signUpButton = findViewById(R.id.btnSignUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String password = passwordEditText.getText().toString();
                String retype = retypeEditText.getText().toString();
                String birthday = birthdayEditText.getText().toString();

                if (!password.equals(retype)) {
                    showToast("Passwords do not match. Please re-enter.");
                }
                else if (!isValidDate(birthday)) {
                    showToast("Please check your birthday format.");
                } else {
                    showToast("Sign-Up successful!");
                }
            }
        });
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
}
