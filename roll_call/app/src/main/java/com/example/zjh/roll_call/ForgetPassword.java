package com.example.zjh.roll_call;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ForgetPassword extends Activity {
    private EditText phone;
    private EditText checkNumber;
    private Button getCheckNumber;
    private Button retrieve;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword);
        initView();
    }
    private void initView(){
        //        初始化按钮对象
        retrieve = (Button)findViewById(R.id.retrieve);
        getCheckNumber = (Button)findViewById(R.id.getCheckNumber);
        phone = (EditText)findViewById(R.id.phone);
        checkNumber = (EditText)findViewById(R.id.checkNumber);
        retrieve.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            }
        });
        getCheckNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
