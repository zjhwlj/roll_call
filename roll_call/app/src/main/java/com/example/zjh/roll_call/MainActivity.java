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

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    private Button loginInButton;
    private TextView signUpEditText;
    private EditText accountEditText;
    private EditText passeowrdEditText;
    private TextView forgrtPassword;
    private String locationProvider;
    private LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Intent i = new Intent(MainActivity.this, Home.class);
                startActivity(i);
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