package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {
    private CardView card_thanhnien, card_vnexpress, card_tuoitre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        card_thanhnien = findViewById(R.id.card_thanh_nien);
        card_vnexpress = findViewById(R.id.card_vnexpress);
        card_tuoitre = findViewById(R.id.card_tuoi_tre);
        SharedPreferences prefs = getSharedPreferences("keyToAccessSH", Context.MODE_PRIVATE);
        String getLastUrl = prefs.getString("keyToAccessString", null);
        if(getLastUrl == null){
            card_thanhnien.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, ThanhNienMainActivity.class));
                }
            });
            card_vnexpress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, VNexpressMainActivity.class));
                }
            });
            card_tuoitre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, TuoitreMainActivity.class));
                }
            });
        }
        else{
            Intent intent = new Intent(HomeActivity.this, WebActivity.class);
            intent.putExtra("url", getLastUrl);
            startActivity(intent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        getSharedPreferences("keyToAccessSH",0).edit().clear().apply();
    }
}