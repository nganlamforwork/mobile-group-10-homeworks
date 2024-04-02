package khtn.hw05;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CustomListFragment extends Fragment implements FragmentCallbacks {
    // this fragment shows a ListView
    CustomListViewAdapter adapter;
    MainActivity main;
    Context context = null;
    TextView choosenPeople;
    ListView peopleList;
    String[] ids = {"9829", "1809", "3509", "3100", "1120","1022", "5264", "8546", "4158", "4147"};
    String[] fullnames = {"Lê Vũ Ngân Lam", "Trần Tùng Lâm", "Võ Quốc Bình", "Trần Đỗ Anh Khoa", "Trần Bình Kha","Lê Văn A", "Trần Thị B", "Võ Văn C", "Trần Văn D", "Trần Thị E"};
    String[] studentClassIds = {"1", "2", "1", "1", "2","3", "3", "5", "5", "2"};
    Integer[] avatars = {R.drawable.user, R.drawable.user2, R.drawable.user3, R.drawable.user4, R.drawable.user5,R.drawable.user, R.drawable.user2, R.drawable.user3, R.drawable.user4, R.drawable.user5};
    String[] classIds = {"1", "2","3","4","5"};
    String[] classNames = {"A1", "A2","C1","C2","B1"};
    private ArrayList<Student> students = new ArrayList<>();

    DatabaseHandler dbHandler;

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
            context = getActivity();
            main = (MainActivity) getActivity();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }

        dbHandler = new DatabaseHandler(getActivity());

        for (int i = 0; i < classIds.length; i++) {
            dbHandler.addClass(new Class(classIds[i], classNames[i]));
        }

        // Thêm dữ liệu cho bảng Student với khóa ngoại là ID của lớp học
        for (int i = 0; i < ids.length; i++) {
            Student student = new Student(ids[i], fullnames[i], studentClassIds[i], avatars[i], i);
            dbHandler.addStudent(student);
        }

        Toast.makeText(getActivity(), "Students added to database", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate res/layout_blue.xml để tạo GUI chứa một TextView và một ListView
        LinearLayout fragment_custom_list = (LinearLayout) inflater.inflate(R.layout.layout_custom_list, null);
        choosenPeople = (TextView) fragment_custom_list.findViewById(R.id.choosenPeople);

        // Tạo một ArrayList để lưu trữ dữ liệu của sinh viên
        ArrayList<Student> students = (ArrayList<Student>) dbHandler.getAllStudents();

        adapter = new CustomListViewAdapter(context, R.layout.row_layout, students);

        // bind intrinsic ListView to custom adapter
        peopleList = (ListView) fragment_custom_list.findViewById(R.id.peopleList);
        peopleList.setAdapter(adapter);

        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choosenPeople.setText("Mã số: " + students.get(position).getMaLop() + "_" + students.get(position).getMaHS());

                main.onMsgFromFragToMain("LIST_FRAG", students.get(position));
                // Update selected item position
                adapter.setSelectedItemPosition(position);

                // Notify the adapter that the data set has changed
                adapter.notifyDataSetChanged();
            }
        });

        return fragment_custom_list;
    }


    private void createStudentObjects() {
        for (int i = 0; i < ids.length; i++) {
            Student student = new Student(ids[i], fullnames[i], studentClassIds[i], avatars[i], i);
            students.add(student);
        }
        Log.d("StudentList", students.toString());
    }

    @Override
    public void onMsgFromMainToFragment(Student student) {
        Student currentStudent = dbHandler.getStudentByPosition(student.getPosition());
        choosenPeople.setText("Mã số: " +  currentStudent.getMaLop() + "_" + currentStudent.getMaHS());
        main.onMsgFromFragToMain("LIST_FRAG", currentStudent);
        // Update selected item position
        adapter.setSelectedItemPosition(student.getPosition());
        adapter.notifyDataSetChanged();
    }
}