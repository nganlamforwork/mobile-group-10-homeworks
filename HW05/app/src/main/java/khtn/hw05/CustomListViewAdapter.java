package khtn.hw05;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListViewAdapter extends ArrayAdapter<Student> {
    Context context;
    private int selectedItemPosition = -1;
    private ArrayList<Student> students;

    public CustomListViewAdapter(Context context, int layoutToBeInflated, ArrayList<Student> students) {
        super(context, R.layout.row_layout, students);
        this.context = context;
        this.students = students;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row;
        if (convertView == null){
            row = inflater.inflate(R.layout.row_layout, null);
        }else{
            row = (View) convertView;
        }
        TextView fullnameLabel = (TextView) row.findViewById(R.id.fullname);
        ImageView avatar = (ImageView) row.findViewById(R.id.avatar);
        fullnameLabel.setText(students.get(position).getId());
        avatar.setImageResource(students.get(position).getAvatar());
//        if (position == selectedItemPosition) {
//            // Set background color for the selected item
//            row.setBackgroundColor(0xffffe18f);
//        } else {
//            // Set default background color for other items
//            row.setBackgroundColor(0xffffffff);
//        }
        if (position == selectedItemPosition) {
            row.setBackgroundResource(R.drawable.diagonal_gradient_background);
        } else {
            row.setBackgroundResource(android.R.color.transparent);
        }

        return (row);
    }

    public void setSelectedItemPosition(int position) {
        selectedItemPosition = position;
    }
}
