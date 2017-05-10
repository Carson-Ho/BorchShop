package scut.carson_ho.borchshop.Initialization;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import scut.carson_ho.borchshop.R;

public class GuideActivity extends Activity  {


    // 欢迎页组件ViewPager变量
    private ViewPager viewpager;
    private Context context;

    //"点"布局
    private LinearLayout layoutDot;
    private LinearLayout.LayoutParams p;

    private List<ImageView> dots = new ArrayList<ImageView>();
    private int currPageIndex;

    // 欢迎页倒计时变量
    private TextView tv_time;

    private int[] imageIds = new int[]{R.drawable.wel_1,
            R.drawable.wel_2, R.drawable.wel_3};

    private List<ImageView> imageViewList = new ArrayList<ImageView>();


    HandlerThread mHandlerThread;
    Handler mainHandler, workHandler;
    int time = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        //加入Activity退出列表
        BaseApplication.addActivity(GuideActivity.this);
        

//        tv_time = (TextView) findViewById(R.id.tv_time);
//
//        // 用于欢迎页的倒计时
//        //创建与主线程关联的Handler
//        mainHandler = new Handler();
//        //创建欢迎页倒计时工作线程
//        initBackground();

        //用SharedPreferences存放系统配置:是否第一次启动
        //参数1:指定文件名称;参数2:设置为默认存储模式:代表该文件是私有数据，只能被应用本身访问
        SharedPreferences setting = getSharedPreferences("WelcomeActivity", 0);

        //数据读取,如果不存在"FIRST"字段,则返回后面值:true
        Boolean user_first = setting.getBoolean("FIRST", true);

        // 用户第1次启动App
        if (user_first) {
            //获取编辑器并写入数据
            setting.edit().putBoolean("FIRST", false).commit();
            // 启动引导页
            theFirstStart();

            // 第(n+1)次启动
            // 启动欢迎页(不启动引导页)

        } else {


//            tv_time.setVisibility(View.VISIBLE);
//            //倒计时
//            tv_time.setText("点击跳过 3s");
//            tv_time.setClickable(true);
//            tv_time.setOnClickListener(this);

            // 启动欢迎页
            Intent localIntent = new Intent();
            localIntent.setClass(GuideActivity.this,
                    WelcomeActivity.class);
            startActivity(localIntent);
        }




    }



//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.tv_time:
//                stepIntoNext();
//                break;
//            default:
//                break;
//        }
//    }


    /**
     * 引导页(第一次启动时调用)
     **/
    @SuppressWarnings("deprecation")
    private void theFirstStart() {

        //初始化点
        initDots();

        //存储卡的创建
        //SdcardConfig.getInstance().initSdcard();

        viewpager = (ViewPager) findViewById(R.id.id_viewpager);

        //viewPager页面滑动的事件
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            Boolean flag = false;

            //在状态改变的时候调用
            //其中arg0参数有三种状态
            //SCROLL_STATE_DRAGGING:(拽拉)页面被拖动/滑动时
            //SCROLL_STATE_IDLE:(空闲) 只要拖动/滑动结束，无论是否安放到了目标页
            //SCROLL_STATE_SETTLING:(安居)通过拖动/滑动，安放到了目标页
            @Override
            public void onPageScrollStateChanged(int arg0) {
                switch (arg0) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        flag = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        flag = true;
                        break;

                    case ViewPager.SCROLL_STATE_IDLE:
                        //如果viewpager翻到最后一页而且有拉扯但是没有滑动完毕的情况，就会启动Mainactivity
                        //划完了就跳转
                        if (viewpager.getCurrentItem() == viewpager.getAdapter()
                                .getCount() - 1 && !flag) {
                            Intent localIntent = new Intent();
                            localIntent.setClass(GuideActivity.this,
                                    WelcomeActivity.class);
                            localIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            startActivity(localIntent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                            GuideActivity.this.finish();
                        }

                        flag = true;
                        break;
                }
            }

            //页面滑动完后调用
            //arg0=当前选中的页面的Position（位置编号）。
            @Override
            public void onPageSelected(int arg0) {
                // 旧点不亮
                dots.get(currPageIndex % imageIds.length).setSelected(false);
                // 新点高亮
                currPageIndex = arg0;
                dots.get(currPageIndex % imageIds.length).setSelected(true);
            }


            // 当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法一直被调用
            //其中三个参数的含义分别为：
            //arg0:当前页面，即你点击滑动的页面
            //arg1:当前页面偏移的百分比
            //arg2:当前页面偏移的像素位置


            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
        });


        //用于设置ViewPager切换时的动画效果:DepthPageTransformer
        viewpager.setPageTransformer(true, new DepthPageTransformer());
        context = this.getApplicationContext();
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            //初始化viewpager的每一个Item
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(context);
                imageView.setImageResource(imageIds[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                imageViewList.add(imageView);
                return imageView;
            }


            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(imageViewList.get(position));
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return imageIds.length;
            }
        });
    }

    //初始化启动页下方的点(动态添加)
    private void initDots() {
//        //设置"点"的参数
//        p = new LinearLayout.LayoutParams(
//                //
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        p.rightMargin = 8;

        //点的布局
        layoutDot = (LinearLayout) findViewById(R.id.layout_dots);
        currPageIndex = imageIds.length * 100;

        //多少个图片,设置多少个点
        for (int i = 0; i < imageIds.length; i++) {
            //创建图,用来装点
            ImageView img = new ImageView(getApplication());
            //点的背景
            img.setBackgroundResource(R.drawable.dot_selector);
            //默认未选中
            img.setSelected(false);

            // .java LinearLayout.LayoutParams 布局参数
            //代码设置"点"的布局
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(

                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            p.rightMargin = 10;

            //动态添加"点"组件到点的布局里
            layoutDot.addView(img, p);
            //将图片加入到一个List里面去
            dots.add(img);
        }
        dots.get(0).setSelected(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //    /**
//     * 多次启动
//     **/
//    private void multipleStart() {
//        Intent localIntent = new Intent();
//        localIntent.setClass(GuideActivity.this,
//                WelcomeActivity.class);


}

//    /**
//     * 跳过直接进入商城主页
//     **/
//
//    private void stepIntoNext() {
//        workHandler.removeCallbacksAndMessages(null);
//        Intent localIntent = new Intent();
//        localIntent.setClass(GuideActivity.this, WebActivity.class);
//        startActivity(localIntent);
//        finish();
//    }


//    /**
//     * 欢迎页倒计时线程
//     **/
//    private void initBackground() {
//
//
//        //通过实例化mHandlerThread从而创建新线程
//        mHandlerThread = new HandlerThread("handlerThread");
//
//        //启动新线程
//        mHandlerThread.start();
//
//
//        //创建与工作线程相关联的Handler,并与mHandlerThread所创建的Looper相关联
//        //实现了Handler与工作线程相关联
//        //下面HandlerMessage的方法体均会在mHandlerThread所创建的工作线程中执行
//        workHandler = new Handler(mHandlerThread.getLooper()) {
//            @Override
//            //消息处理的操作
//            public void handleMessage(Message msg) {
//                //通过msg来进行识别
//
//                if (msg.what == 1) {
//
//                    if (time > 0) {
//                        mainHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                tv_time.setText("点击跳过 " + time + "s");
//                                time--;
//                            }
//                        });
//
//                    }
//
//                    if (time == 0) {
//                        stepIntoNext();//跳转主页
//                        return;
//                    }
//
//                    Message msg2 = workHandler.obtainMessage();// obtain=get 是一种优化写�?
//                    // 内部查找可重用的Message 如果有就重用;没有才创建新
//                    msg2.what = 1;
//
//                    workHandler.sendMessageDelayed(msg2, 1000);
//                    // 循环发送消息
//                }
//            }
//
//
//        };
//
//    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mainHandler.removeCallbacksAndMessages(null);
//        workHandler.removeCallbacksAndMessages(null);
//    }

