package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class VNexpressMainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<String> rssLinks = new ArrayList<>();
    private Button btnThoisuExpress, btnThegioiExpress, btnDulichExpress, btnGiaitriExpress, btnThethaoExpress, btnDoisongExpress,
            btnPhapluatExpress, btnStartupExpress, btnGiaoducExpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_nexpress_main);
        btnThoisuExpress = findViewById(R.id.btnThoiSuExpress);
        btnThoisuExpress.setOnClickListener(this);
        btnThegioiExpress = findViewById(R.id.btnTheGioiExpress);
        btnThegioiExpress.setOnClickListener(this);
        btnDulichExpress = findViewById(R.id.btnDuLichExpress);
        btnDulichExpress.setOnClickListener(this);
        btnGiaitriExpress = findViewById(R.id.btnGiaiTriExpress);
        btnGiaitriExpress.setOnClickListener(this);
        btnThethaoExpress = findViewById(R.id.btnTheThaoExpress);
        btnThethaoExpress.setOnClickListener(this);
        btnDoisongExpress = findViewById(R.id.btnDoiSongExpress);
        btnDoisongExpress.setOnClickListener(this);
        btnPhapluatExpress = findViewById(R.id.btnPhapLuatExpress);
        btnPhapluatExpress.setOnClickListener(this);
        btnStartupExpress = findViewById(R.id.btnStartupExpress);
        btnStartupExpress.setOnClickListener(this);
        btnGiaoducExpress = findViewById(R.id.btnGiaoDucExpress);
        btnGiaoducExpress.setOnClickListener(this);
        rssLinks.add("https://vnexpress.net/rss/thoi-su.rss");
        rssLinks.add("https://vnexpress.net/rss/the-gioi.rss");
        rssLinks.add("https://vnexpress.net/rss/du-lich.rss");
        rssLinks.add("https://vnexpress.net/rss/giai-tri.rss");
        rssLinks.add("https://vnexpress.net/rss/the-thao.rss");
        rssLinks.add("https://vnexpress.net/rss/gia-dinh.rss");
        rssLinks.add("https://vnexpress.net/rss/phap-luat.rss");
        rssLinks.add("https://vnexpress.net/rss/startup.rss");
        rssLinks.add("https://vnexpress.net/rss/giao-duc.rss");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, RSSFeedActivity.class);
        switch(view.getId()) {
            case R.id.btnThoiSuExpress:
                intent.putExtra("rssLink", rssLinks.get(0));
                startActivity(intent);
                break;
            case R.id.btnTheGioiExpress:
                intent.putExtra("rssLink", rssLinks.get(1));
                startActivity(intent);
                break;
            case R.id.btnDuLichExpress:
                intent.putExtra("rssLink", rssLinks.get(2));
                startActivity(intent);
                break;
            case R.id.btnGiaiTriExpress:
                intent.putExtra("rssLink", rssLinks.get(3));
                startActivity(intent);
                break;
            case R.id.btnTheThaoExpress:
                intent.putExtra("rssLink", rssLinks.get(4));
                startActivity(intent);
                break;
            case R.id.btnDoiSongExpress:
                intent.putExtra("rssLink", rssLinks.get(5));
                startActivity(intent);
                break;
            case R.id.btnPhapLuatExpress:
                intent.putExtra("rssLink", rssLinks.get(6));
                startActivity(intent);
                break;
            case R.id.btnStartupExpress:
                intent.putExtra("rssLink", rssLinks.get(7));
                startActivity(intent);
                break;
            case R.id.btnGiaoDucExpress:
                intent.putExtra("rssLink", rssLinks.get(8));
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        getSharedPreferences("keyToAccessSH",0).edit().clear().apply();
    }
}