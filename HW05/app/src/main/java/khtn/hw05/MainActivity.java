package khtn.hw05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements MainCallbacks {
    FragmentTransaction ft;
    DetailFragment detailFragment;
    CustomListFragment customListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a new LIST fragment - show it
        ft = getSupportFragmentManager().beginTransaction();
        customListFragment = CustomListFragment.newInstance("first-blue");
        ft.replace(R.id.list_view_fragment, customListFragment);
        ft.commit();

        // create a new DETAIL fragment - show it
        ft = getSupportFragmentManager().beginTransaction();
        detailFragment = DetailFragment.newInstance("");
        ft.replace(R.id.detail_view_fragment, detailFragment);
        ft.commit();
    }

    // MainCallback implementation (receiving messages coming from Fragments)
    @Override
    public void onMsgFromFragToMain(String sender, Student student) {
        // show message arriving to MainActivity
        Toast.makeText(getApplication(), " MAIN GOT>> " + sender + "\n" + student.getId(), Toast.LENGTH_LONG).show();
        if (sender.equals("-FRAG")) { /* TODO: if needed, do here something on behalf of the RED fragment*/ }
        if (sender.equals("LIST_FRAG")) {
            try {
                // forward list-data to detailFragment using its callback method
                detailFragment.onMsgFromMainToFragment(student);
            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
    }
}