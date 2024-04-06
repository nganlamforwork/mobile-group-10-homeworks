package hcmus.android.hw08;
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
        String category = "";

        if (view.getId() == R.id.btnThoiSu) {
            intent.putExtra("rssLink", rssLinks.get(0));
            category = "Thời sự";
        } else if (view.getId() == R.id.btnTheGioi) {
            intent.putExtra("rssLink", rssLinks.get(1));
            category = "Thế giới";
        } else if (view.getId() == R.id.btnVanHoa) {
            intent.putExtra("rssLink", rssLinks.get(2));
            category = "Văn hóa";
        } else if (view.getId() == R.id.btnGiaiTri) {
            intent.putExtra("rssLink", rssLinks.get(3));
            category = "Giải trí";
        } else if (view.getId() == R.id.btnTheThao) {
            intent.putExtra("rssLink", rssLinks.get(4));
            category = "Thể thao";
        } else if (view.getId() == R.id.btnDoiSong) {
            intent.putExtra("rssLink", rssLinks.get(5));
            category = "Đời sống";
        } else if (view.getId() == R.id.btnTaiChinhKinhDoanh) {
            intent.putExtra("rssLink", rssLinks.get(6));
            category = "Tài chính kinh doanh";
        } else if (view.getId() == R.id.btnGioiTre) {
            intent.putExtra("rssLink", rssLinks.get(7));
            category = "Giới trẻ";
        } else if (view.getId() == R.id.btnGiaoDuc) {
            intent.putExtra("rssLink", rssLinks.get(8));
            category = "Giáo dục";
        }

        intent.putExtra("category", category);
        intent.putExtra("news", "Thanh niên");
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getSharedPreferences("keyToAccessSH",0).edit().clear().apply();
    }
}