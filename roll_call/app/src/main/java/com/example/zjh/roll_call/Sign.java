package com.example.zjh.roll_call;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class Sign extends AppCompatActivity {
    private static final double EARTH_RADIUS = 6378.137;
    private String locationProvider;
    private String jw;
    private int flag;
    public static int isdect=0;
    private TextView statute;
    Calendar cal;
    private LocationManager locationManager;
    private EditText Longitude_and_latitudeEdit;
    private Button getLongAndLa;
    private Button startSign;
    //定位权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);
        cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        initView();
    }
    private void initView(){
        locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        //        初始化按钮对象
        getLongAndLa = (Button)findViewById(R.id.getLongAndLa);
        startSign =(Button)findViewById(R.id.startsign);
        statute=(TextView)findViewById(R.id.statute);
        flag = 0;
        Longitude_and_latitudeEdit = (EditText)findViewById(R.id.Longitude_and_latitude);
        getLongAndLa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Criteria criteria = new Criteria();
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
                    ActivityCompat.requestPermissions(Sign.this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
                    Toast.makeText(Sign.this, "onCreate: 没有权限 ",
                            Toast.LENGTH_SHORT).show();
                    statute.setText("签到失败,无法获取位置");
                    return;
                }
                Location location = locationManager.getLastKnownLocation(locationProvider);
                if (location == null) {
                    Toast.makeText(Sign.this, "无法获取地理位置为,请打开定位 ",
                            Toast.LENGTH_LONG).show();
                }
                if (location != null) {
                 /*   Toast.makeText(Sign.this, "定位成功------->"+"location------>经度为：" + location.getLatitude() + "\n纬度为" + location.getLongitude(),
                            Toast.LENGTH_SHORT).show();*/
//                    jw = String.format("%.2f", location.getLatitude())+ " , "+ String.format("%.2f",location.getLongitude());
                    jw = String.valueOf(location.getLatitude())+ " , "+ String.valueOf(location.getLongitude());
                    flag = 1;
                    //不为空,显示地理位置经纬度
                    Longitude_and_latitudeEdit.setText(jw);
                }
            }
        });
        startSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                if (ContextCompat.checkSelfPermission(Sign.this, Manifest.permission.CAMERA)

                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(Sign.this,

                            new String[]{Manifest.permission.CAMERA}, 1);

                } else {
                    if(((hour * 60 + minute) >=480 && (hour * 60 + minute )<=510 )|| ((hour* 60 + minute) >=690 && (hour * 60 + minute )<= 720)) {
                    if (flag == 1) {
                        startActivity(new Intent(Sign.this, DetectActivity.class));
                        if(isdect ==1) {
                            Toast.makeText(Sign.this, "签到成功", Toast.LENGTH_LONG).show();
                            statute.setText("签到成功");
                            Drawable drawable = getResources().getDrawable(R.drawable.hs);
                            startSign.setBackground(drawable);
                            flag = 2;
                        }
                        else{
                            Toast.makeText(Sign.this, "人脸检测失败", Toast.LENGTH_LONG).show();
                            statute.setText("签到成功，人脸检测失败");
                        }
                    } else if (flag == 0) {
                        Toast.makeText(Sign.this, "请先获取位置", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Sign.this, "请勿重复签到", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Sign.this, hour+":"+minute+"不在签到时间内,上课前半小时和下课前半小时可签到",Toast.LENGTH_LONG).show();
                        statute.setText("签到失败，不在签到时间内,上课前半小时和下课前半小时可签到");
                }
                }

            }
        });
    }
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    // 返回单位是:米
    public static double getDistance(double longitude1, double latitude1,
                                     double longitude2, double latitude2) {
        double Lat1 = rad(latitude1);
        double Lat2 = rad(latitude2);
        double a = Lat1 - Lat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(Lat1) * Math.cos(Lat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        //有小数的情况;注意这里的10000d中的“d”
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;//单位：米
        return s;
    }
    public void setButtonImgStyle(ImageButton button, int drawable, int color) {
        Drawable originalDrawable = ContextCompat.getDrawable(this, drawable);
        Drawable tintDrawable = tintDrawable(originalDrawable, ContextCompat.getColor(this, color));
        button.setImageDrawable(tintDrawable);
    }
    private Drawable tintDrawable(Drawable drawable, int colors) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(wrappedDrawable, colors);
        return wrappedDrawable;
    }
}
