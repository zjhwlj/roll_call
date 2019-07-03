package com.example.zjh.roll_call;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.SMSSDK;

public  class HomeForStudent extends Activity {//implements ImageUtil.CropHandler
    private List<Notice> noticeList = new ArrayList<Notice>();
    private Button searchcourse;
    private ImageButton back;
    private DrawerLayout mDrawerLayout;
    private ImageView head;
    private LinearLayout chooseLinearLayout;
    private DrawerLayout left;
    private TextView changephone;
    private TextView changpassword;
    private TextView suggest;
    private TextView aboutus;
    private TextView score;
    public static String[] courselist2 = MainActivity.courselist;
    String PATH=android.os.Environment.getExternalStorageDirectory()+
            "/"+"msg";
    public static final String NAME = "yourhead.jpg";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home);
        setContentView(R.layout.home);
        searchcourse=(Button) findViewById(R.id.searchcourse);
        back = (ImageButton)findViewById(R.id.back);
       /* mDrawerLayout = findViewById(R.id.dl_left);
         head = findViewById(R.id.head);
        chooseLinearLayout = (LinearLayout) findViewById(R.id.chooseLinearLayout);
        if(getLoacalBitmap(PATH+"/"+NAME)!=null){
            Bitmap temp = getLoacalBitmap(PATH+"/"+NAME);
            head.setImageBitmap(temp);
        }
        else{Toast.makeText(Home.this, PATH+'/'+NAME,
                Toast.LENGTH_SHORT).show();}
       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                showDrawerLayout();
            }
        });*/
        initNotice();
        NoticeAdapter adapter = new NoticeAdapter(HomeForStudent.this,
                R.layout.notice, noticeList);
        left=(DrawerLayout)findViewById(R.id.dl_left);
        changephone =(TextView)findViewById(R.id.changphone);
        changpassword = (TextView)findViewById(R.id.changpassword);
        suggest = (TextView)findViewById(R.id.suggest);
        aboutus = (TextView)findViewById(R.id.aboutus);
        score = (TextView) findViewById(R.id.score);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
             /*   Notice notice = noticeList.get(position);
                Toast.makeText(Home.this, notice.getName(),
                        Toast.LENGTH_SHORT).show();*/
                Intent i = new Intent(HomeForStudent.this, Sign.class);
                startActivity(i);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeForStudent.this);
                builder.setMessage("要加入该课程吗");
                builder.setTitle("提示");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    //设置确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); //关闭dialog
                        Toast.makeText(HomeForStudent.this,"加入成功",Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                //参数都设置完成了，创建并显示出来
                builder.create().show();
                return false;
            }
        });
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(HomeForStudent.this);
                builder.setTitle("告示"); //设置标题
                builder.setMessage("程序员正在加紧开发中，头发都要掉光了"); //设置内容
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
        changephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeForStudent.this, ChangePhone.class);
                startActivity(i);
            }
        });
        changpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeForStudent.this, ChangePassword.class);
                startActivity(i);
            }
        });
        suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeForStudent.this, Suggest.class);
                startActivity(i);
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(HomeForStudent.this);
                builder.setTitle("关于我们"); //设置标题
                builder.setMessage("苦逼程序员"); //设置内容
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left.openDrawer(Gravity.LEFT);//侧滑打开  不设置则不会默认打开
            }
        });
        searchcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeForStudent.this,"暂未查询到相关信息",Toast.LENGTH_SHORT);
                /*Intent i = new Intent(HomeForStudent.this, AddCourse.class);
                startActivity(i);*/
            }
        });
//        head.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showChoosePicDialog();
//            }
//        });
    }
    private void showDrawerLayout() {
        if (!left.isDrawerOpen(Gravity.LEFT)) {
            left.openDrawer(Gravity.LEFT);
        } else {
            left.closeDrawer(Gravity.LEFT);
        }
    }

    /**
     * 显示修改图片的对话框

     protected void showChoosePicDialog() {
     AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
     builder.setTitle("添加图片");
     String[] items = { "选择本地照片"};
     builder.setNegativeButton("取消", null);
     builder.setItems(items, new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
    switch (which) {
    case 0: // 选择本地照片
    Intent galleryIntent = ImageUtil
    .getCropHelperInstance()
    .buildGalleryIntent();
    startActivityForResult(galleryIntent,
    ImageUtil.REQUEST_GALLERY);
    break;
    }
    }
    });
     builder.show();
     }  */
    private void initNotice(){
        int i;
        for(i=0;i<courselist2.length/3;i++) {
            Notice course = new Notice(courselist2[i*3],courselist2[i*3+1],courselist2[i*3+2]);
            noticeList.add(course);
        }
/*        Notice course2 = new Notice("Apple", "陈xx",21312312);
        noticeList.add(course2);
        Notice course3 = new Notice("Apple", "陈xx",21312312);
        noticeList.add(course3);
        Notice course4 = new Notice("Apple", "陈xx",21312312);
        noticeList.add(course4);
        Notice course5 = new Notice("Apple", "陈xx",21312312);
        noticeList.add(course5);
        Notice course6 = new Notice("Apple", "陈xx",21312312);
        noticeList.add(course6);
        Notice course7 = new Notice("Apple", "陈xx",21312312);
        noticeList.add(course7);*/
    }
   /* private void showDrawerLayout() {
        if (!mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
        else {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageUtil.getCropHelperInstance().sethandleResultListerner((ImageUtil.CropHandler) Home.this, requestCode, resultCode,
                data);
    }
    @Override
    public void onPhotoCropped(Bitmap photo,int requestCode) {
        switch (requestCode){
            case ImageUtil.RE_GALLERY:
                saveImage(photo,PATH,NAME);
                head.setImageBitmap(photo);
                break;
        }
    }
    @Override
    public void onCropCancel() {

    }
    @Override
    public void onCropFailed(String message) {

    }
    @Override
    public Activity getContext() {
        return Home.this;
    }
    //保存图片到本地路径
    public File saveImage(Bitmap bmp, String path, String fileName) {
        File appDir = new File(path);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, fos);
            fos.flush();
            fos.close();
            Snackbar
                    .make(chooseLinearLayout, "保存头像成功", Snackbar.LENGTH_LONG)
                    .setAction("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
         *//*   Toast.makeText(Home.this, "保存成功",
                    Toast.LENGTH_SHORT).show();*//*
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(Home.this, "保存失败"+e,
                    Toast.LENGTH_SHORT).show();
            Log.i("Home", "保存失败"+e);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(Home.this, "保存头像失败"+e,
                    Toast.LENGTH_SHORT).show();
            Log.i("Home", "保存头像失败"+e);
        }
        return file;
    }

    *//** * @Title: getLoacalBitmap * @Description: 加载本地图片 * @param @param url 本地路径 * @param @return * @return Bitmap * @throws *//*
    public static Bitmap getLoacalBitmap(String url) {
        if (url != null) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(url);
                return BitmapFactory.decodeStream(fis); // /把流转化为Bitmap图片
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } finally {
                if(fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fis = null;
                }
            }
        } else {
            return null;
        }
    }*/
}
