package com.example.zjh.roll_call;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import zxing.activity.CaptureActivity;
import zxing.encoding.EncodingHandler;

public class ExampleUnitTest extends AppCompatActivity{
    private Button creat;
    private Button scan;
    private TextView scanresult;
    private ImageView QRcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcourse);
        creat=(Button)findViewById(R.id.creat);
        scan=(Button)findViewById(R.id.scan);
        QRcode=(ImageView)findViewById(R.id.QRcode);
        scanresult=(TextView)findViewById(R.id.scanresult);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExampleUnitTest.this, CaptureActivity.class);
                startActivityForResult(intent,0);
            }
        });

        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = "陈东阳是我儿子";
                if (string.equalsIgnoreCase(""))
                {
                    Toast.makeText(ExampleUnitTest.this,"请输入文本",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try {
                        //生成二维码图片
                        Bitmap qrcode = EncodingHandler.createQRCode(string,600);
                        QRcode.setImageBitmap(qrcode);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            //获取二维码扫描结果
            String string = data.getExtras().getString("result");
            scanresult.setText(string);
        }
    }
}
