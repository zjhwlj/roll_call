package com.example.zjh.roll_call;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class ChangePhone extends Activity {
    private EditText newphone;
    private EditText yzm;
    private Button getyzm;
    private Button confim;
    private static final int CODE_REPEAT = 1; //重新发送
    public String country = "86";//这是中国区号，如果需要其他国家列表，可以使用getSupportedCountries();获得国家区号
    private int TIME = 60;//倒计时60s这里应该多设置些因为mob后台需要60s,我们前端会有差异的建议设置90，100或者120
    private TimerTask tt;
    private Timer tm;
    int flag = 0;
    private String phonenumber;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home);
        setContentView(R.layout.changephone);
        newphone = (EditText)findViewById(R.id.newphone);
        yzm = (EditText) findViewById(R.id.yzm);
        getyzm =(Button)findViewById(R.id.getyzm);
        confim =(Button)findViewById(R.id.confim);
        confim.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String code = yzm.getText().toString();
                if (!TextUtils.isEmpty(code)) {//判断验证码是否为空                    //验证
                    SMSSDK.submitVerificationCode(country, phonenumber, code);
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ChangePhone.this);
                    builder.setTitle("通知"); //设置标题
                    builder.setMessage("修改成功"); //设置内容
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
                } else {//如果用户输入的内容为空，提醒用户
                    toast("请输入验证码");
                }
            }
        });
        yzm.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    String code = yzm.getText().toString();
                    if (!TextUtils.isEmpty(code)) {//判断验证码是否为空                    //验证
                        SMSSDK.submitVerificationCode(country, phonenumber, code);
                        flag = 1;
                    } else {//如果用户输入的内容为空，提醒用户
                        toast("请输入验证码");
                    }
                }
            }
        });
        getyzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonenumber = newphone.getText().toString();
                if(phonenumber!=null)
                    alterWarning();
                else
                    toast("请输入手机号");
            }
        });
    }
    @SuppressLint("HandlerLeak")
    Handler hd = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CODE_REPEAT) {
                tm.cancel();//取消任务
                tt.cancel();//取消任务
                TIME = 60;//时间重置
                getyzm.setText("重新发送验证码");
            } else {
                getyzm.setText(TIME + "s重新发送验证码");
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
                    flag = 1;
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
    //弹窗确认下发
    private void alterWarning() {        //构造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示"); //设置标题
        builder.setMessage("我们将要发送到" + phonenumber + "验证"); //设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                // 通过sdk发送短信验证（请求获取短信验证码，在监听（eh）中返回）
                SMSSDK.getVerificationCode(country, phonenumber);                //做倒计时操作
                Toast.makeText(ChangePhone.this, "已发送" + which, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ChangePhone.this, "已取消" + which, Toast.LENGTH_SHORT).show();
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
    //吐司的一个小方法
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ChangePhone.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
