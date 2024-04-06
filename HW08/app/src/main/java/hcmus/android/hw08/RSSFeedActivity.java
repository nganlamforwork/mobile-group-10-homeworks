package hcmus.android.hw08;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class RSSFeedActivity extends ListActivity {
    ArrayList<HashMap<String, String>> rssItemList = new ArrayList<>();
    RSSParser rssParser = new RSSParser();
    List<RSSItem> rssItems = new ArrayList<>();
    private String TAG_TITLE = "title", TAG_LINK = "link", TAG_DATE = "pubDate";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed);
        String rss_link = getIntent().getStringExtra("rssLink");
        new LoadRSSFeedItems().execute(rss_link);
        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showNiceDialogBox(rssItems.get(position), view.getContext());
            }
        });
    }

    public void showNiceDialogBox(RSSItem selectedStoryItem, Context context) {

        String title = selectedStoryItem.getTitle();
        String description = selectedStoryItem.getDescription();
        if (title.toLowerCase().equals(description.toLowerCase())) {
            description = "";
        }
        try {
            //CAUTION: sometimes TITLE and DESCRIPTION include HTML markers
            final Uri storyLink = Uri.parse(selectedStoryItem.getLink());
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(this);
            myBuilder
                    .setTitle(title)
                    .setMessage(Html.fromHtml(description) + "\n")
                    .setPositiveButton("Close", null)
                    .setNegativeButton("More", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Assuming R.id.page_url is in the custom layout of the dialog
                            String pageUrl = selectedStoryItem.getLink();

                            Intent intent = new Intent(RSSFeedActivity.this, WebActivity.class);
                            intent.putExtra("url", pageUrl);
                            startActivity(intent);
                        }
                    })
                    .show();

        } catch (Exception e) {
            Log.e("Error DialogBox", e.getMessage());
        }
    }

    public class LoadRSSFeedItems extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... args) {
            String rss_url = args[0];
            rssItems = rssParser.getRSSFeedItems(rss_url);
            for (final RSSItem item : rssItems) {
                if (item.link.equals("")) break;
                HashMap<String, String> map = new HashMap<>();
                String givenDateString = item.pubdate.trim();
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.US);
                try {
                    Date mDate = sdf.parse(givenDateString);
                    SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE, dd MMMM yyyy 'at' hh:mm a", Locale.US);
                    item.pubdate = sdf2.format(mDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                map.put(TAG_TITLE, item.title);
                map.put(TAG_LINK, item.link);
                map.put(TAG_DATE, item.pubdate);
                rssItemList.add(map);
            }
            runOnUiThread(new Runnable() {
                public void run() {
                    ListAdapter adapter = new SimpleAdapter(RSSFeedActivity.this, rssItemList, R.layout.rss_item_list_row,
                            new String[]{TAG_LINK, TAG_TITLE, TAG_DATE}, new int[]{R.id.page_url, R.id.title, R.id.pub_date});
                    setListAdapter(adapter);
                }
            });
            return null;
        }
    }
}
