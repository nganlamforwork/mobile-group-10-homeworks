package khtn.hw05;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment implements FragmentCallbacks {
    MainActivity main;
    TextView txtStudentId, txtHoTen, txtLop, txtDTB;
    Button firstBtn, prevBtn, nextBtn, lastBtn;
    int studentLength = 4;
    Student student;

    public static DetailFragment newInstance(String strArg1) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("arg1", strArg1);
        fragment.setArguments(bundle);

        return fragment;
    } // newInstance

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Activities containing this fragment must implement interface: MainCallbacks
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException("Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity(); // use this reference to invoke main callbacks
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflate res/layout_detail.xml which includes a textview and a button
        LinearLayout detail_layout = (LinearLayout) inflater.inflate(R.layout.layout_detail, null);
        txtStudentId = (TextView) detail_layout.findViewById(R.id.studentId);
        txtLop = (TextView) detail_layout.findViewById(R.id.studentClass);
        txtHoTen = (TextView) detail_layout.findViewById(R.id.studentFullname);
        firstBtn = (Button) detail_layout.findViewById(R.id.firstBtn);
        prevBtn = (Button) detail_layout.findViewById(R.id.prevBtn);
        nextBtn = (Button) detail_layout.findViewById(R.id.nextBtn);
        lastBtn = (Button) detail_layout.findViewById(R.id.lastBtn);

        // show string argument supplied by constructor (if any!)
        try {
            Bundle arguments = getArguments();
            txtStudentId.setText(arguments.getString("arg1", ""));
        } catch (Exception e) {
            Log.e("DETAIL BUNDLE ERROR – ", "" + e.getMessage());
        }
        // clicking the button changes the time displayed and sends a copy to MainActivity

        firstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student = new Student("", "", "", 0, 0);
                main.onMsgFromFragToMain("DETAIL_FRAG", student);
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student = new Student("", "", "",  0, student.getPosition() - 1);
                main.onMsgFromFragToMain("DETAIL_FRAG", student);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student = new Student("", "", "", 0, student.getPosition() + 1);
                main.onMsgFromFragToMain("DETAIL_FRAG", student);
            }
        });

        lastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student = new Student("", "", "", 0, studentLength);
                main.onMsgFromFragToMain("DETAIL_FRAG", student);
            }
        });


        return detail_layout;
    }

    @Override
    public void onMsgFromMainToFragment(Student student) {
        // receiving a message from MainActivity (it may happen at any point in time)
        this.student = student;
        txtStudentId.setText(student.getMaHS());
        txtLop.setText(student.getMaLop());
        txtHoTen.setText(student.getTenHS());
        if (student.getPosition() == 0) {
            firstBtn.setEnabled(false);
            prevBtn.setEnabled(false);
            nextBtn.setEnabled(true);
            lastBtn.setEnabled(true);
        } else if (student.getPosition() == studentLength) {
            firstBtn.setEnabled(true);
            prevBtn.setEnabled(true);
            nextBtn.setEnabled(false);
            lastBtn.setEnabled(false);
        } else {
            firstBtn.setEnabled(true);
            prevBtn.setEnabled(true);
            nextBtn.setEnabled(true);
            lastBtn.setEnabled(true);
        }
    }
}

