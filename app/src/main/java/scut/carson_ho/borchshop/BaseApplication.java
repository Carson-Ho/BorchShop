package scut.carson_ho.borchshop;

import android.app.Application;
import android.content.Context;

/**
 * Created by Carson_Ho on 17/2/28.
 * 自定义Application
 *
 * 注意:需要在清单文件中声明使用当前类
 *
 * <application android:name="包名.BaseApplication" >
 */
public class BaseApplication extends Application {

    
        private static Context context;
        private static int mainThreadId;

        // 当应用创建的时候,调用此方法
        @Override
        public void onCreate() {
        super.onCreate();
        // 1. 获取Context
        context = getApplicationContext();
        // 2. 获取主线程id
        mainThreadId = android.os.Process.myTid();
        // 初始化图片加载类
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

}
