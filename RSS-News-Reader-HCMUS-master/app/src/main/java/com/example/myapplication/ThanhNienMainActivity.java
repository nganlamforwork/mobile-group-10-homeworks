package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ThanhNienMainActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<String> rssLinks = new ArrayList<>();
    private Button btnThoisu, btnThegioi, btnVanhoa, btnGiaitri, btnThethao, btnDoisong, btnTaichinhkinhdoanh, btnGioitre, btnGiaoduc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thanh_nien);
        btnThoisu = findViewById(R.id.btnThoiSu);
        btnThoisu.setOnClickListener(this);
        btnThegioi = findViewById(R.id.btnTheGioi);
        btnThegioi.setOnClickListener(this);
        btnVanhoa = findViewById(R.id.btnVanHoa);
        btnVanhoa.setOnClickListener(this);
        btnGiaitri = findViewById(R.id.btnGiaiTri);
        btnGiaitri.setOnClickListener(this);
        btnThethao = findViewById(R.id.btnTheThao);
        btnThethao.setOnClickListener(this);
        btnDoisong = findViewById(R.id.btnDoiSong);
        btnDoisong.setOnClickListener(this);
        btnTaichinhkinhdoanh = findViewById(R.id.btnTaiChinhKinhDoanh);
        btnTaichinhkinhdoanh.setOnClickListener(this);
        btnGioitre = findViewById(R.id.btnGioiTre);
        btnGioitre.setOnClickListener(this);
        btnGiaoduc = findViewById(R.id.btnGiaoDuc);
        btnGiaoduc.setOnClickListener(this);
        rssLinks.add("https://thanhnien.vn/rss/thoi-su.rss");
        rssLinks.add("https://thanhnien.vn/rss/the-gioi.rss");
        rssLinks.add("https://thanhnien.vn/rss/van-hoa.rss");
        rssLinks.add("https://thanhnien.vn/rss/giai-tri.rss");
        rssLinks.add("https://thethao.thanhnien.vn/rss/home.rss");
        rssLinks.add("https://thanhnien.vn/rss/doi-song.rss");
        rssLinks.add("https://thanhnien.vn/rss/tai-chinh-kinh-doanh.rss");
        rssLinks.add("https://thanhnien.vn/rss/gioi-tre.rss");
        rssLinks.add("https://thanhnien.vn/rss/giao-duc.rss");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, RSSFeedActivity.class);
        switch(view.getId()) {
            case R.id.btnThoiSu:
                intent.putExtra("rssLink", rssLinks.get(0));
                startActivity(intent);
                break;
            case R.id.btnTheGioi:
                intent.putExtra("rssLink", rssLinks.get(1));
                startActivity(intent);
                break;
            case R.id.btnVanHoa:
                intent.putExtra("rssLink", rssLinks.get(2));
                startActivity(intent);
                break;
            case R.id.btnGiaiTri:
                intent.putExtra("rssLink", rssLinks.get(3));
                startActivity(intent);
                break;
            case R.id.btnTheThao:
                intent.putExtra("rssLink", rssLinks.get(4));
                startActivity(intent);
                break;
            case R.id.btnDoiSong:
                intent.putExtra("rssLink", rssLinks.get(5));
                startActivity(intent);
                break;
            case R.id.btnTaiChinhKinhDoanh:
                intent.putExtra("rssLink", rssLinks.get(6));
                startActivity(intent);
                break;
            case R.id.btnGioiTre:
                intent.putExtra("rssLink", rssLinks.get(7));
                startActivity(intent);
                break;
            case R.id.btnGiaoDuc:
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