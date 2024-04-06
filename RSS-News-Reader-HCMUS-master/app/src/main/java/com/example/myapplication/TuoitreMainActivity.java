package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class TuoitreMainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<String> rssLinks = new ArrayList<>();
    private Button btnSuckhoeTuoitre, btnCongngheTuoitre, btnKinhdoanhTuoitre, btnVanhoaTuoitre, btnThugianTuoitre, btnPhapluatTuoitre,
            btnDulichTuoitre, btnKhoahocTuoitre, btnThethaoTuoitre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuoitre_main);
        btnSuckhoeTuoitre = findViewById(R.id.btnSuckhoeTuoitre);
        btnSuckhoeTuoitre.setOnClickListener(this);
        btnCongngheTuoitre = findViewById(R.id.btnCongngheTuoitre);
        btnCongngheTuoitre.setOnClickListener(this);
        btnKinhdoanhTuoitre = findViewById(R.id.btnKinhdoanhTuoitre);
        btnKinhdoanhTuoitre.setOnClickListener(this);
        btnVanhoaTuoitre = findViewById(R.id.btnVanhoaTuoitre);
        btnVanhoaTuoitre.setOnClickListener(this);
        btnThugianTuoitre = findViewById(R.id.btnThugianTuoitre);
        btnThugianTuoitre.setOnClickListener(this);
        btnPhapluatTuoitre = findViewById(R.id.btnPhapluatTuoitre);
        btnPhapluatTuoitre.setOnClickListener(this);
        btnDulichTuoitre = findViewById(R.id.btnDulichTuoitre);
        btnDulichTuoitre.setOnClickListener(this);
        btnKhoahocTuoitre = findViewById(R.id.btnKhoahocTuoitre);
        btnKhoahocTuoitre.setOnClickListener(this);
        btnThethaoTuoitre = findViewById(R.id.btnThethaoTuoitre);
        btnThethaoTuoitre.setOnClickListener(this);
        rssLinks.add("https://tuoitre.vn/rss/suc-khoe.rss");
        rssLinks.add("https://tuoitre.vn/rss/nhip-song-so.rss");
        rssLinks.add("https://tuoitre.vn/rss/kinh-doanh.rss");
        rssLinks.add("https://tuoitre.vn/rss/van-hoa.rss");
        rssLinks.add("https://tuoitre.vn/rss/thu-gian.rss");
        rssLinks.add("https://tuoitre.vn/rss/phap-luat.rss");
        rssLinks.add("https://tuoitre.vn/rss/du-lich.rss");
        rssLinks.add("https://tuoitre.vn/rss/khoa-hoc.rss");
        rssLinks.add("https://tuoitre.vn/rss/the-thao.rss");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, RSSFeedActivity.class);
        switch(view.getId()) {
            case R.id.btnSuckhoeTuoitre:
                intent.putExtra("rssLink", rssLinks.get(0));
                startActivity(intent);
                break;
            case R.id.btnCongngheTuoitre:
                intent.putExtra("rssLink", rssLinks.get(1));
                startActivity(intent);
                break;
            case R.id.btnKinhdoanhTuoitre:
                intent.putExtra("rssLink", rssLinks.get(2));
                startActivity(intent);
                break;
            case R.id.btnVanhoaTuoitre:
                intent.putExtra("rssLink", rssLinks.get(3));
                startActivity(intent);
                break;
            case R.id.btnThugianTuoitre:
                intent.putExtra("rssLink", rssLinks.get(4));
                startActivity(intent);
                break;
            case R.id.btnPhapluatTuoitre:
                intent.putExtra("rssLink", rssLinks.get(5));
                startActivity(intent);
                break;
            case R.id.btnDulichTuoitre:
                intent.putExtra("rssLink", rssLinks.get(6));
                startActivity(intent);
                break;
            case R.id.btnKhoahocTuoitre:
                intent.putExtra("rssLink", rssLinks.get(7));
                startActivity(intent);
                break;
            case R.id.btnThethaoTuoitre:
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