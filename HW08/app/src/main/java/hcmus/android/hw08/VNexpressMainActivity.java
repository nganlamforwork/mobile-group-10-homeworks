package hcmus.android.hw08;

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
        intent.putExtra("News", "VN Express");

        String category = "";
        if (view.getId() == R.id.btnThoiSuExpress) {
            intent.putExtra("rssLink", rssLinks.get(0));
            category = "Thời sự";
        } else if (view.getId() == R.id.btnTheGioiExpress) {
            intent.putExtra("rssLink", rssLinks.get(1));
            category = "Thế giới";
        } else if (view.getId() == R.id.btnDuLichExpress) {
            intent.putExtra("rssLink", rssLinks.get(2));
            category = "Du lịch";
        } else if (view.getId() == R.id.btnGiaiTriExpress) {
            intent.putExtra("rssLink", rssLinks.get(3));
            category = "Giải trí";
        } else if (view.getId() == R.id.btnTheThaoExpress) {
            intent.putExtra("rssLink", rssLinks.get(4));
            category = "Thể thao";
        } else if (view.getId() == R.id.btnDoiSongExpress) {
            intent.putExtra("rssLink", rssLinks.get(5));
            category = "Đời sống";
        } else if (view.getId() == R.id.btnPhapLuatExpress) {
            intent.putExtra("rssLink", rssLinks.get(6));
            category = "Pháp luật";
        } else if (view.getId() == R.id.btnStartupExpress) {
            intent.putExtra("rssLink", rssLinks.get(7));
            category = "Startup";
        } else if (view.getId() == R.id.btnGiaoDucExpress) {
            intent.putExtra("rssLink", rssLinks.get(8));
            category = "Giáo dục";
        }

        intent.putExtra("category", category);
        intent.putExtra("news", "VN Express");
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getSharedPreferences("keyToAccessSH",0).edit().clear().apply();
    }
}