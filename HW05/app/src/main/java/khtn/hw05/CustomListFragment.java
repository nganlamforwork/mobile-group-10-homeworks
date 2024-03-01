package khtn.hw05;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CustomListFragment extends Fragment {
    // this fragment shows a ListView
    MainActivity main;
    Context context = null;
    TextView choosenPeople;
    ListView peopleList;
    // The n-th row in the list will consist of [icon, label] where icon = thumbnail[n] and label=items[n]
    String[] ids = {"A1_9829", "A1_1809", "A2_3509", "A2_3100", "A1_1120"};
    String[] fullnames = {"Lê Vũ Ngân Lam", "Trần Tùng Lâm", "Võ Quốc Bình", "Trần Đỗ Anh Khoa", "Trần Bình Kha"};
    double[] gpas = {4.2, 4.3, 4.4, 4.5, 4.6};
    Integer[] avatars = {R.drawable.user, R.drawable.user2, R.drawable.user3, R.drawable.user4, R.drawable.user5};
    private ArrayList<Student> students = new ArrayList<>();

    // convenient constructor(accept arguments, copy them to a bundle, binds bundle to fragment)
    public static CustomListFragment newInstance(String strArg) {
        CustomListFragment fragment = new CustomListFragment();
        Bundle args = new Bundle();
        args.putString("strArgs1", strArg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createStudentObjects();
        try {
            context = getActivity(); // use this reference to invoke main callbacks
            main = (MainActivity) getActivity();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// inflate res/layout_blue.xml to make GUI holding a TextView and a ListView
        LinearLayout fragement_custom_list = (LinearLayout) inflater.inflate(R.layout.layout_custom_list, null);
        choosenPeople = (TextView) fragement_custom_list.findViewById(R.id.choosenPeople);

        Log.d("StudentList", students.toString());
        // the arguments of the custom adapter are: activityContex, layout-to-be-inflated, labels, icons
        CustomListViewAdapter adapter = new CustomListViewAdapter(context, R.layout.row_layout, students);

        // bind intrinsic ListView to custom adapter
        peopleList = (ListView) fragement_custom_list.findViewById(R.id.peopleList);
        peopleList.setAdapter(adapter);

        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choosenPeople.setText("Mã số: " + ids[position]);
                main.onMsgFromFragToMain("LIST_FRAG", students.get(position));
                // Update selected item position
                adapter.setSelectedItemPosition(position);

                // Notify the adapter that the data set has changed
                adapter.notifyDataSetChanged();
            }
        });
        return fragement_custom_list;
    }


    private void createStudentObjects() {
        for (int i = 0; i < ids.length; i++) {
            // Splitting the fullnames string to get class and id
            String[] parts = ids[i].split("_");

            // Assuming fullnames have a valid format (e.g., "A1_9829")
            if (parts.length == 2) {
                String className = parts[0];

                // Creating a Student object and adding to the array
                Student student = new Student(ids[i], fullnames[i], className, gpas[i], avatars[i]);
                students.add(student);
            }
        }

        Log.d("StudentList", students.toString());
    }


}