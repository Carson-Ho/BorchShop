package scut.carson_ho.borchshop.Initialization;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;

import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

import scut.carson_ho.borchshop.Web.WebviewEntity;

/**
 * Created by Carson_Ho on 17/2/28.
 * 自定义Application
 * 用于对APP启动时候进行初始化
 * 用于存放全局所需的类对象，提供静态方法获取
 * 注意:需要在清单文件中声明使用当前类
 *
 * <application android:name="包名.BaseApplication" >
 */
public class BaseApplication extends Application {


    // 使用自己APP的ID（官网注册的）
    private static final String APP_ID = "2882303761517554928";
    // 使用自己APP的Key（官网注册的）
    private static final String APP_KEY = "5731755451928";

    private static Context context;
    private static int mainThreadId;

    private static WebviewEntity mWebView;

    // 当应用创建的时候,调用此方法
    @Override
    public void onCreate() {
        super.onCreate();
        // 1. 获取Context
        context = getApplicationContext();
        // 2. 获取主线程id
        mainThreadId = android.os.Process.myTid();
        // 初始化图片加载类

        mWebView = new WebviewEntity(getContext());
        mWebView.loadMessageUrl("http://121.40.100.57/mobile");


        if (shouldInit()) {
            //注册推送服务
            //注册成功后会向DemoMessageReceiver发送广播
            // 可以从DemoMessageReceiver的onCommandResult方法中MiPushCommandMessage对象参数中获取注册信息
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
        }
    }

    /**
     * 获取上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取主线程
     *
     * @return
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }


    //通过判断手机里的所有进程是否有这个App的进程
    //从而判断该App是否有打开

    private boolean shouldInit() {

        //通过ActivityManager我们可以获得系统里正在运行的activities
        //包括进程(Process)等、应用程序/包、服务(Service)、任务(Task)信息。
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();

        //获取本App的唯一标识
        int myPid = Process.myPid();
        //利用一个增强for循环取出手机里的所有进程
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            //通过比较进程的唯一标识和包名判断进程里是否存在该App
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public static WebviewEntity getmWebView(){
        return mWebView;
    }

    /*
     * 设置WebView实例
     */
    public static void setmWebView(WebviewEntity webviewEntity){
        mWebView = webviewEntity;
    }
}
