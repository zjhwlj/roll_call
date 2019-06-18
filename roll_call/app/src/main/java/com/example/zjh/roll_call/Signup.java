package com.example.zjh.roll_call;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.mob.MobSDK;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class Signup extends Activity {

    private RadioButton studentRadioButton;
    private RadioButton teacherRadioButton;
    private EditText phenoNumber;
    private EditText signupCheck;
    private EditText password;
    private EditText confimPassword;
    private Button getSignupcheck;
    private Button signup;
    private Button toLoginIn;
    private TimerTask tt;
    private Timer tm;
    private int flag;
    private boolean viable;
    private String id;
    private int TIME = 60;//倒计时60s这里应该多设置些因为mob后台需要60s,我们前端会有差异的建议设置90，100或者120
    public String country = "86";//这是中国区号，如果需要其他国家列表，可以使用getSupportedCountries();获得国家区号
    private String phone;
    private String mima;
    private int identify;
    private String confmima;
    private static final int CODE_REPEAT = 1; //重新发送

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        initView();
        signup.setClickable(false);
        identify = 1;
        viable = false;
        flag = 0;
        MobSDK.init(this, "2a708387d1372", "61829c092c24ed33ec902a4fa2214f47");
        SMSSDK.registerEventHandler(eh); //注册短信回调（记得销毁，避免泄露内存）
        phenoNumber.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    phone = phenoNumber.getText().toString().trim().replaceAll("/s", "");
                    if (!TextUtils.isEmpty(phone)) {                   //定义需要匹配的正则表达式的规则
                        String REGEX_MOBILE_SIMPLE = "[1][358]\\d{9}";                    //把正则表达式的规则编译成模板
                        Pattern pattern = Pattern.compile(REGEX_MOBILE_SIMPLE);                    //把需要匹配的字符给模板匹配，获得匹配器
                        Matcher matcher = pattern.matcher(phone);                    // 通过匹配器查找是否有该字符，不可重复调用重复调用matcher.find()
                        if (matcher.find()) {//匹配手机号是否存在
                        } else {
                            toast("手机号格式错误");
                        }
                    } else {
                        toast("请先输入手机号");
                    }
                }
            }
        });
        getSignupcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterWarning();
            }
        });
        signupCheck.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    String code = signupCheck.getText().toString().replaceAll("/s", "");
                    if (!TextUtils.isEmpty(code)) {//判断验证码是否为空                    //验证
                        SMSSDK.submitVerificationCode(country, phone, code);
                        flag = 1;
                    } else {//如果用户输入的内容为空，提醒用户
                        toast("请输入验证码");
                    }
                }
            }
        });
        confimPassword.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    mima = password.getText().toString();
                    confmima = confimPassword.getText().toString();
                    if (mima != null && confmima != null) {
                        if (mima.equals(confmima) && flag == 1) {
                            viable = true;
                        } else {
                            toast("两次密码不一致");
                        }
                    } else {
                        toast("请输入密码");
                    }
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viable == true) {
                    Toast.makeText(Signup.this, "注册成功", Toast.LENGTH_SHORT).show();
                    FileOutputStream fos=null;
                    try {
                        fos=openFileOutput("login",MODE_PRIVATE);
                        //把这些信息写入
                        fos.write((phone+" "+mima).getBytes());
                        fos.flush();//刷新
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }finally {
                        if(fos!=null){
                            try {
                                fos.close();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    Toast.makeText(Signup.this, "尚有信息未完善", Toast.LENGTH_SHORT).show();
                }
            }
        });
        toLoginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viable == true) {
                    FileInputStream fis=null;
                    byte[] buffer=null;
                    try {
                        fis=openFileInput("login");
                        buffer=new  byte[fis.available()];
                        fis.read(buffer);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }finally {
                        if(fis!=null){
                            try {
                                fis.close();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }
                    String data=new String(buffer);
                    String name=data.split(" ")[0];
                    String id=data.split(" ")[1];
                    toast(name+ " "+ id);
               /*     Intent i = new Intent(Signup.this, MainActivity.class);
                    startActivity(i);*/
                }
            }
        });

    }
    private void initView() {
        studentRadioButton = (RadioButton) findViewById(R.id.studentButtonId);
        teacherRadioButton = (RadioButton) findViewById(R.id.teacherButtonId);
        phenoNumber = (EditText) findViewById(R.id.phenoNumber);
        signupCheck = (EditText) findViewById(R.id.signupCheck);
        password = (EditText) findViewById(R.id.password);
        confimPassword = (EditText) findViewById(R.id.confimPassword);
        getSignupcheck = (Button) findViewById(R.id.getSignupCheck);
        signup = (Button) findViewById(R.id.signup);
        toLoginIn = (Button) findViewById(R.id.toLoginIn);
        if (studentRadioButton.isChecked()) {
//            Toast.makeText(Signup.this,"身份是:学生",Toast.LENGTH_SHORT).show();
            identify = 1;
        }
        if (teacherRadioButton.isChecked()) {
//            Toast.makeText(Signup.this,"身份是:老师",Toast.LENGTH_SHORT).show();
            identify = 0;
        }
    }

    @SuppressLint("HandlerLeak")
    Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CODE_REPEAT) {
                getSignupcheck.setEnabled(true);
                tm.cancel();//取消任务
                tt.cancel();//取消任务
                TIME = 60;//时间重置
                getSignupcheck.setText("重新发送验证码");
            } else {
                getSignupcheck.setText(TIME + "重新发送验证码");
            }
        }
    };
    //回调
    EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    toast("验证成功");
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    toast("获取验证码成功");
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //如果你调用了获取国家区号类表会在这里回调
                    // 返回支持发送验证码的国家列表
                }
            } else {
                //错误等在这里（包括验证失败）
                // 错误码请参照http://wiki.mob.com/android-api-错误码参考/这里我就不再继续写了
                ((Throwable) data).printStackTrace();
                String str = data.toString();
                toast(str);
            }
        }
    };

    //吐司的一个小方法
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Signup.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //弹窗确认下发
    private void alterWarning() {        //构造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        phone = phenoNumber.getText().toString().trim().replaceAll("/s", "");
        builder.setTitle("提示"); //设置标题
        builder.setMessage("我们将要发送到" + phone + "验证"); //设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                // 通过sdk发送短信验证（请求获取短信验证码，在监听（eh）中返回）
                SMSSDK.getVerificationCode(country, phone);                //做倒计时操作
                Toast.makeText(Signup.this, "已发送" + which, Toast.LENGTH_SHORT).show();
                getSignupcheck.setEnabled(false);
                tm = new Timer();
                tt = new TimerTask() {
                    @Override
                    public void run() {
                        hd.sendEmptyMessage(TIME--);
                    }
                };
                tm.schedule(tt, 0, 1000);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(Signup.this, "已取消" + which, Toast.LENGTH_SHORT).show();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }

    //销毁短信注册
    @Override
    protected void onDestroy() {
        super.onDestroy();        // 注销回调接口registerEventHandler必须和unregisterEventHandler配套使用，否则可能造成内存泄漏。
        SMSSDK.unregisterEventHandler(eh);
    }

    private void sign() {
        mima = password.getText().toString();
      /*  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        id = simpleDateFormat.format(date);*/
        id = getMyUUID();
        String sql = "insert into user(account,signnum,course,notice,identify,id) values(" + phone  + "," + 0 + "," + "' '" + "," + "' '" + "," + identify + ",'" + id + "');insert into userkey(account,password,id) values("+ phone + "," + mima+ ",'" + id + "');";
        Log.i("Signup", sql);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    java.sql.Connection cn = DriverManager.getConnection("jdbc:mysql://172.17.173.191/roll_call", "root", "123");
//                    String sql = "insert into user(account,signnum,course,notice,identify,id) values(" + phone  + "," + 0 + "," + "' '" + "," + "' '" + "," + identify + ",'" + id + "');insert into userkey(account,password,id) values("+ phone + "," + mima+ ",'" + id + "');";
                    String sql ="select * from user";
                    Log.i("Signup", sql);
                    Statement st = (Statement) cn.createStatement();
                    boolean rs = st.execute(sql);
                    if (rs != false) {
                        /* String result = rs.getString(2);*/
                        Intent i = new Intent(Signup.this, MainActivity.class);
                        startActivity(i);
                    }
                    Log.i("Signup", "访问数据库成功");
                    cn.close();
                    st.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    Log.i("Signup", "加载jdbc失败" + e);
                } catch (SQLException e) {
                    e.printStackTrace();
                    Log.i("Signup", "连接数据库失败" + e);
                }
            }
        }).start();
    }
    private String getMyUUID(){
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        Log.d("Signup","----->UUID"+uuid);
        return uniqueId;
    }

}
