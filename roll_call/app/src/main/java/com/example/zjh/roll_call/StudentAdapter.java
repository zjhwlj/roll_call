package com.example.zjh.roll_call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends ArrayAdapter {
    private int resourceId;

    public StudentAdapter(Context context, int textViewResourceId, List<Notice> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Notice notice = (Notice) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView noticeName = (TextView) view.findViewById(R.id.studentName);
//        noticeImage.setImageResource(notice.getImageId());
        noticeName.setText("15900000003");
        return view;
    }
}
