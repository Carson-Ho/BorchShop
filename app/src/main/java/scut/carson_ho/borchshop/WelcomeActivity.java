package scut.carson_ho.borchshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import scut.carson_ho.borchshop.Guiders.GuiderActivity1;

/**
 * Created by Carson_Ho on 17/3/7.
 */
public class WelcomeActivity extends Activity implements View.OnClickListener {


    // 欢迎页倒计时变量
    private Button tv_time;


    HandlerThread mHandlerThread;
    Handler mainHandler, workHandler;
    int time = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tv_time = (Button) findViewById(R.id.tv_time);

        // 用于欢迎页的倒计时
        //创建与主线程关联的Handler
        mainHandler = new Handler();
        //创建欢迎页倒计时工作线程
        initBackground();

        tv_time.setVisibility(View.VISIBLE);
        //倒计时
        tv_time.setText("点击跳过 2s");
        tv_time.setClickable(true);
        tv_time.setOnClickListener(this);


        Message msg = workHandler.obtainMessage();
        msg.what = 1;
        workHandler.sendMessageDelayed(msg, 1000);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_time:
                stepIntoNext();
                break;
            default:
                break;
        }
    }



    /**
     * 跳过直接进入商城主页
     **/

    private void stepIntoNext() {
        workHandler.removeCallbacksAndMessages(null);
        Intent localIntent = new Intent();
        localIntent.setClass(WelcomeActivity.this, GuiderActivity1.class);
        startActivity(localIntent);
        finish();
    }



    /**
     * 欢迎页倒计时线程
     **/
    private void initBackground() {


        //通过实例化mHandlerThread从而创建新线程
        mHandlerThread = new HandlerThread("handlerThread");

        //启动新线程
        mHandlerThread.start();


        //创建与工作线程相关联的Handler,并与mHandlerThread所创建的Looper相关联
        //实现了Handler与工作线程相关联
        //下面HandlerMessage的方法体均会在mHandlerThread所创建的工作线程中执行
        workHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            //消息处理的操作
            public void handleMessage(Message msg) {
                //通过msg来进行识别

                if (msg.what == 1) {

                    if (time > 0) {
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                tv_time.setText("点击跳过 " + time + "s");
                                time--;
                            }
                        });

                    }

                    if (time == 0) {
                        stepIntoNext();//跳转主页
                        return;
                    }

                    Message msg2 = workHandler.obtainMessage();// obtain=get 是一种优化写�?
                    // 内部查找可重用的Message 如果有就重用;没有才创建新
                    msg2.what = 1;

                    workHandler.sendMessageDelayed(msg2, 1000);
                    // 循环发送消息
                }
            }


        };

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainHandler.removeCallbacksAndMessages(null);
        workHandler.removeCallbacksAndMessages(null);
    }
}

