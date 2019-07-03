package com.example.zjh.roll_call;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
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

public class HomeForTeacher extends Activity {
    private List<Notice> noticeList = new ArrayList<Notice>();
    private Button addcourse;
    private DrawerLayout mDrawerLayout;
    private ImageView head;
    public static  int flag =0;
    private TextView changephone;
    private TextView changpassword;
    private ImageButton back;
    private TextView suggest;
    private TextView aboutus;
    private TextView score;
    private DrawerLayout left;
    public static String[] courselist1 = MainActivity.courselist;
    private LinearLayout chooseLinearLayout;
    String PATH=android.os.Environment.getExternalStorageDirectory()+
            "/"+"msg";
    public static final String NAME = "yourhead.jpg";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home);
        setContentView(R.layout.home1);
        addcourse=(Button) findViewById(R.id.addcourse);
        changephone =(TextView)findViewById(R.id.changphone);
        left=(DrawerLayout)findViewById(R.id.dl_left);
        changpassword = (TextView)findViewById(R.id.changpassword);
        suggest = (TextView)findViewById(R.id.suggest);
        aboutus = (TextView)findViewById(R.id.aboutus);
        back = (ImageButton)findViewById(R.id.back);
        score = (TextView) findViewById(R.id.score);
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
       if(flag ==0){
           initNotice();
           flag =1;
       }
        NoticeAdapter adapter = new NoticeAdapter(HomeForTeacher.this,
                R.layout.notice, noticeList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
             /*   Notice notice = noticeList.get(position);
                Toast.makeText(Home.this, notice.getName(),
                        Toast.LENGTH_SHORT).show();*/
                Intent i = new Intent(HomeForTeacher.this, StudentInfo.class);
                startActivity(i);
            }
        });
        addcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeForTeacher.this, AddCourse.class);
                startActivity(i);
            }
        });
        changephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeForTeacher.this, ChangePhone.class);
                startActivity(i);
            }
        });
        changpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeForTeacher.this, ChangePassword.class);
                startActivity(i);
            }
        });
        suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeForTeacher.this, Suggest.class);
                startActivity(i);
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(HomeForTeacher.this);
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
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(HomeForTeacher.this);
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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left.openDrawer(Gravity.LEFT);//侧滑打开  不设置则不会默认打开
            }
        });
//        head.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showChoosePicDialog();
//            }
//        });
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
        noticeList = new ArrayList<Notice>();
        for(i=0;i<courselist1.length/3;i++) {
            Notice course = new Notice(courselist1[i*3], courselist1[i*3+1], courselist1[i*3+2]);
            noticeList.add(course);
        }
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
    @Override

    protected void onResume() {

        super.onResume();

        onCreate(null);

    }
}
