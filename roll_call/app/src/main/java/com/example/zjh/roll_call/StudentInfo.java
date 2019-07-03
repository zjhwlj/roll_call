package com.example.zjh.roll_call;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentInfo extends Activity {
    private List<Notice> noticeList = new ArrayList<Notice>();
    private Button startsign;
    private ListView studentinfo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home);
        setContentView(R.layout.studentinfo);
        startsign = (Button)findViewById(R.id.startsign);
        studentinfo = (ListView)findViewById(R.id.studentinfo);
        noticeList = new ArrayList<Notice>();
        Notice course = new Notice("15900000003", "868", " 213 ");
        noticeList.add(course);
        StudentAdapter adapter = new StudentAdapter(StudentInfo.this,
                R.layout.studentlist, noticeList);
        studentinfo.setAdapter(adapter);
        studentinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
             /*   Notice notice = noticeList.get(position);
                Toast.makeText(Home.this, notice.getName(),
                        Toast.LENGTH_SHORT).show();*/
     /*           Intent i = new Intent(HomeForTeacher.this, Sign.class);
                startActivity(i);*/
            }
        });
        startsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudentInfo.this,"开始签到",Toast.LENGTH_LONG).show();
                Drawable drawable = getResources().getDrawable(R.drawable.hs);
                startsign.setBackground(drawable);
            }
        });
    }
}
