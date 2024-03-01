package khtn.hw05;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment implements FragmentCallbacks {
    MainActivity main;
    TextView txtStudentId, txtHoTen, txtLop, txtDTB;

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
        txtDTB = (TextView) detail_layout.findViewById(R.id.studentGpa);
        // show string argument supplied by constructor (if any!)
        try {
            Bundle arguments = getArguments();
            txtStudentId.setText(arguments.getString("arg1", ""));
        } catch (Exception e) {
            Log.e("DETAIL BUNDLE ERROR – ", "" + e.getMessage());
        }
        // clicking the button changes the time displayed and sends a copy to MainActivity

        return detail_layout;
    }

    @Override
    public void onMsgFromMainToFragment(Student student) {
        // receiving a message from MainActivity (it may happen at any point in time)
        txtStudentId.setText(student.getId());
        txtLop.setText(student.getClassName());
        txtHoTen.setText(student.getFullName());
        txtDTB.setText(String.valueOf(student.getGpa()));
    }
}

