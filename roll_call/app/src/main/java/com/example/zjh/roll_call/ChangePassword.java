package com.example.zjh.roll_call;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword  extends Activity {
    private EditText ymm;
    private EditText xmm;
    private EditText zcsr;
    private Button xg;
    String mypassword;
    String p1;
    String p2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home);
        setContentView(R.layout.changpassword);
        ymm = (EditText)findViewById(R.id.ymm);
        xmm = (EditText)findViewById(R.id.xmm);
        zcsr = (EditText)findViewById(R.id.zcsr);
        xg = (Button) findViewById(R.id.xg);
        xg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypassword=ymm.getText().toString();
                if(mypassword.equals("123456")){
                    p1=xmm.getText().toString();
                    p2=zcsr.getText().toString();
                    if(p1.equals(p2)&&!p1.equals("")){
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ChangePassword.this);
                        builder.setTitle("通知"); //设置标题
                        builder.setMessage("密码修改成功"); //设置内容
                        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            //设置确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss(); //关闭dialog
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    }
                    else{
                        Toast.makeText(ChangePassword.this,"2次密码不一致",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(ChangePassword.this,"密码错误",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
