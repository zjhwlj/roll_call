package com.example.zjh.roll_call;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import zxing.activity.CaptureActivity;
import zxing.encoding.EncodingHandler;

public class AddCourse extends AppCompatActivity {
    private Button creat;
//    private Button scan;
    private TextView scanresult;
    private ImageView QRcode;
    String PATH=android.os.Environment.getExternalStorageDirectory()+
            "/"+"msg";
    public static final String NAME = "yourhead.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcourse);
        creat=(Button)findViewById(R.id.creat);
//        scan=(Button)findViewById(R.id.scan);
        QRcode=(ImageView)findViewById(R.id.QRcode);
//        scanresult=(TextView)findViewById(R.id.scanresult);
        String[] ctype = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        Spinner spinner = super.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
       /* scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCourse.this, CaptureActivity.class);
                startActivityForResult(intent,0);
            }
        });
*/
        creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = "陈东阳是我儿子";
                if (string.equalsIgnoreCase(""))
                {
                    Toast.makeText(AddCourse.this,"请输入文本",Toast.LENGTH_SHORT).show();
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
        QRcode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showdialog();
                return false;
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
    public void showdialog(){
        //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        AlertDialog.Builder builder = new AlertDialog.Builder(AddCourse.this);
        //    设置Title的图标
//        builder.setIcon(R.drawable.ic_launcher);
        //    设置Title的内容
//        builder.setTitle("弹出警告框");
        //    设置Content来显示一个信息
        builder.setMessage("保存到相册？");
        //    设置一个PositiveButton
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bitmap image = ((BitmapDrawable)QRcode.getDrawable()).getBitmap();
                saveImage(AddCourse.this,image,PATH,NAME);
            }
        });
        //    设置一个NegativeButton
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(AddCourse.this, "negative: " + which, Toast.LENGTH_SHORT).show();
            }
        });
        //    设置一个NeutralButton
        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(AddCourse.this, "neutral: " + which, Toast.LENGTH_SHORT).show();
            }
        });
        //    显示出该对话框
        builder.show();
    }
    public void saveImage(Context context, Bitmap bmp, String path, String fileName) {
        File appDir = new File(path);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(AddCourse.this, "保存成功",
                    Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(AddCourse.this, "保存失败"+e,
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(AddCourse.this, "保存失败了"+e,
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getPath())));
    }
}
