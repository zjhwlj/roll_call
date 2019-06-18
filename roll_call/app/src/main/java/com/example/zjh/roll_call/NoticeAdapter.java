package com.example.zjh.roll_call;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class NoticeAdapter extends ArrayAdapter {
    private int resourceId;

    public NoticeAdapter(Context context, int textViewResourceId, List<Notice> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Notice notice = (Notice) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView noticeImage = (ImageView) view.findViewById(R.id.coursePic);
        TextView noticeName = (TextView) view.findViewById(R.id.courseNmae);
        TextView noticeIntro = (TextView) view.findViewById(R.id.teacherName);
//        noticeImage.setImageResource(notice.getImageId());
        noticeIntro.setText(notice.getsubName());
        noticeName.setText(notice.getName());
        return view;
    }
}
