package com.example.zjh.roll_call;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import static cn.smssdk.utils.a.e;

public class MainActivity extends AppCompatActivity {
    private Button loginInButton;
    private TextView signUpEditText;
    private EditText accountEditText;
    private EditText passeowrdEditText;
    private TextView forgrtPassword;
    private String locationProvider;
    public static String[] courselist;
    private LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileInputStream fis = null;
        byte[] buffer = null;
        try {
            fis = openFileInput("courinfo");
            buffer = new byte[fis.available()];
            fis.read(buffer);
            String data = new String(buffer);
            //定义数组
            courselist = data.split(" ");
            System.out.print(" 读取成功 " + data.toString());
            //数组转换成list
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            FileOutputStream fos=null;
            try {
                fos=openFileOutput("courinfo",MODE_PRIVATE);
                //把这些信息写入
                fos.write(("人工智能"+" "+"8:30-12:00"+" "+"21323").getBytes());
                courselist=new String []{"人工智能","8:30-12:00","21323"};
                fos.flush();//刷新
            }catch (FileNotFoundException e2){
                e2.printStackTrace();
                Toast.makeText(MainActivity.this, "存储失败"+e, Toast.LENGTH_SHORT).show();
            }catch (IOException e3){
                e3.printStackTrace();
                Toast.makeText(MainActivity.this, "存储失败"+e, Toast.LENGTH_SHORT).show();
            }finally {
                if(fos!=null){
                    try {
                        fos.close();
                    }catch (IOException e1){
                        e1.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "读取失败" + e, Toast.LENGTH_LONG).show();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        initView();
    }
    private void initView(){
        locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        //        初始化按钮对象
        loginInButton = (Button)findViewById(R.id.login);
        signUpEditText = (TextView)findViewById(R.id.sign);
        accountEditText = (EditText)findViewById(R.id.account);
        passeowrdEditText = (EditText)findViewById(R.id.password);
        forgrtPassword = (TextView) findViewById(R.id.forgrtPassword);
        signUpEditText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Signup.class);
                startActivity(i);
            }
        });
        loginInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String account = accountEditText.getText().toString();
                String password = passeowrdEditText.getText().toString();
                if(account!=null&&password!=null) {
                    if (account.equals("15900000003") && password.equals("123456")) {
                        Intent i = new Intent(MainActivity.this, HomeForStudent.class);
                        startActivity(i);
                    } else if (account.equals("15900000002") && password.equals("123456")) {
                        Intent i = new Intent(MainActivity.this, HomeForTeacher.class);
                        startActivity(i);
                    } else {
                        FileInputStream fis = null;
                        byte[] buffer = null;
                        try {
                            fis = openFileInput("login");
                            buffer = new byte[fis.available()];
                            fis.read(buffer);
                            String data = new String(buffer);
                            //定义数组
                            String aa[] = data.split(" ");
                            System.out.print(" 读取成功 " + data.toString());
                            //数组转换成list
                            List<String> userinfolist = Arrays.asList(aa);
//                        Toast.makeText(MainActivity.this,userinfolist.get(0)+ account,Toast.LENGTH_LONG).show();
                            if (userinfolist.contains(account)) {
                                int a = userinfolist.indexOf(account);
                                Log.e("Main", a + " ");
//                            Toast.makeText(MainActivity.this,a,Toast.LENGTH_SHORT).show();
                                String realpassword = aa[a + 1];
                                String identity = aa[a + 2];
//                            Toast.makeText(MainActivity.this,userinfolist.get(0)+ realpassword,Toast.LENGTH_LONG).show();
                                if (realpassword.equals(password)) {
                                    int n = Integer.parseInt(identity);
                                    if (n == 1) {
                                        Intent i = new Intent(MainActivity.this, HomeForStudent.class);
                                        startActivity(i);
                                    } else {
                                        Intent i = new Intent(MainActivity.this, HomeForTeacher.class);
                                        startActivity(i);
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "账号或密码错误", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "账号不存在", Toast.LENGTH_LONG).show();
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "该手机暂未注册" + e, Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "读取失败" + e, Toast.LENGTH_LONG).show();
                        } finally {
                            if (fis != null) {
                                try {
                                    fis.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"请输入账号密码",Toast.LENGTH_SHORT).show();
                }
            }
        });
        forgrtPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ForgetPassword.class);
                startActivity(i);
            }
        });
      /*  Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);//低精度，如果设置为高精度，依然获取不了location。
        criteria.setAltitudeRequired(false);//不要求海拔
        criteria.setBearingRequired(false);//不要求方位
        criteria.setCostAllowed(true);//允许有花费
        criteria.setPowerRequirement(Criteria.POWER_LOW);//低功耗

        //从可用的位置提供器中，匹配以上标准的最佳提供器
        locationProvider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "onCreate: 没有权限 ",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location == null) {
            Toast.makeText(MainActivity.this, "获取地理位置为NULL,请打开定位 ",
                    Toast.LENGTH_LONG).show();
        }
        if (location != null) {
            Toast.makeText(MainActivity.this, "定位成功------->"+"location------>经度为：" + location.getLatitude() + "\n纬度为" + location.getLongitude(),
                    Toast.LENGTH_SHORT).show();
            //不为空,显示地理位置经纬度
        }*/
    }
}