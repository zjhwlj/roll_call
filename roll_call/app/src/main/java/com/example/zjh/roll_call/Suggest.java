package com.example.zjh.roll_call;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Suggest extends Activity {
    private Button forsure;
    private Button cnacel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home);
        setContentView(R.layout.suggest);
        forsure =(Button)findViewById(R.id.forSure);
        cnacel =(Button)findViewById(R.id.cancel);
        forsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Suggest.this);
                builder.setTitle("通知"); //设置标题
                builder.setMessage("提交成功，谢谢你的反馈"); //设置内容
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
        });
        cnacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Suggest.this.finish();
            }
        });
    }
}
