package com.example.hw04;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class CustomListDemoActivity extends Activity {
    TextView choosenPeople;
    ListView peopleList;
    // The n-th row in the list will consist of [icon, label] where icon = thumbnail[n] and label=items[n]
    String[] fullnames = {"Nguyễn Văn A", "Lê Thị B", "Trần Văn C", "Phan Văn C", "Đinh Văn D","Nguyễn Văn A", "Lê Thị B", "Trần Văn C", "Phan Văn C", "Đinh Văn D","Nguyễn Văn A", "Lê Thị B", "Trần Văn C", "Phan Văn C", "Đinh Văn D"};
    String[] phones = {"0989897873", "0967995843", "0907955843", "0967885811", "0988885231", "0989897873", "0967995843", "0907955843", "0967885811", "0988885231", "0989897873", "0967995843", "0907955843", "0967885811", "0988885231"};
    Integer[] avatars = {R.drawable.user, R.drawable.user2, R.drawable.user3, R.drawable.user4, R.drawable.user5, R.drawable.user, R.drawable.user2, R.drawable.user3, R.drawable.user4, R.drawable.user5, R.drawable.user, R.drawable.user2, R.drawable.user3, R.drawable.user4, R.drawable.user5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_demo);
        choosenPeople = (TextView) findViewById(R.id.choosenPeople);

        // the arguments of the custom adapter are: activityContex, layout-to-be-inflated, labels, icons
        CustomListViewAdapter adapter = new CustomListViewAdapter(this, R.layout.row_layout, fullnames, phones, avatars);

        // bind intrinsic ListView to custom adapter
        peopleList = (ListView) findViewById(R.id.peopleList);
        peopleList.setAdapter(adapter);

        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choosenPeople.setText("You choose: " + fullnames[position]);
                // Update selected item position
                adapter.setSelectedItemPosition(position);

                // Notify the adapter that the data set has changed
                adapter.notifyDataSetChanged();
            }
        });

    }


}