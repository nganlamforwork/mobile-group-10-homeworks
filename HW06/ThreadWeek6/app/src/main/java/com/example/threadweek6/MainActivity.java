package com.example.threadweek6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
    ProgressBar myBarHorizontal;
    TextView progressLabel;
    EditText txtDataBox;
    Button btnDoItAgain;

    int globalVar = 0, accum = 0, progressStep = 1;
    long startingMills = System.currentTimeMillis();
    boolean isRunning = false;
    final int MAX_PROGRESS = 100; // int nWork = 1000;

    int data = 0;
    Handler myHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDoItAgain = findViewById(R.id.btnDoItAgain);
        txtDataBox = findViewById(R.id.editText);
        progressLabel = findViewById(R.id.progressLabel);
        myBarHorizontal= findViewById(R.id.myProgressBar);

        btnDoItAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStart();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        String inputText = txtDataBox.getText().toString();
        if (!inputText.isEmpty()) {
            try {
                data = Integer.parseInt(inputText);
                accum = 0;
                myBarHorizontal.setMax(MAX_PROGRESS);
                myBarHorizontal.setProgress(0);
                myBarHorizontal.setVisibility(View.VISIBLE);
                btnDoItAgain.setEnabled(false);
                progressLabel.setText("0%");


                Thread myBackgroundThread = new Thread(backgroundTask, "progressAlias");
                myBackgroundThread.start();
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid long value
                Log.e("NumberFormatException", "Invalid input: " + inputText);
            }
        } else {
            // Handle the case where the input is empty
            Log.e("EmptyInputException", "Input is empty");
        }
    }

    private Runnable backgroundTask = new Runnable() {
        @Override
        public void run() {
            try {
                for (int n = 0; n < 100; n++) {
                    Thread.sleep(data / 1000);
                    myHandler.post(foregroundRunnable);
                }
            } catch (InterruptedException e) {
                Log.e("<<foregroundTask>>", e.getMessage());
            }
        }// run
    };// backgroundTask

    private Runnable foregroundRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                // update UI, observe globalVar is changed in back thread
                myBarHorizontal.incrementProgressBy(progressStep);
                accum += progressStep;
                progressLabel.setText(accum + "%"); // Update the progress label with the current progress

                if (accum >= myBarHorizontal.getMax()) {
                    btnDoItAgain.setEnabled(true);
                }

            } catch (Exception e) {
            }
        }
    }; // foregroundTask
}
