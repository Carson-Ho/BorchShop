package scut.carson_ho.borchshop;

import android.content.Context;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;

/**
 * Created by Carson_Ho on 17/2/28.
 * @Description: 屏幕分辨率工具类
 */
public class ScreenUtil {


    /**
     * 屏幕宽高
     *
     * @param context
     * @return
     */
    private static int[] getScreenSize() {
        DisplayMetrics dm = BaseApplication.getContext().getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        return new int[] { screenWidth, screenHeight };
    }

    /**
     * @Title: getStatusBarHeight
     * @Description: 获取状态栏高度
     * @param context
     * @return int
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * @Title: getScreenWidth
     * @Description: 获取手机屏幕的宽度
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    public static int getScreenWidth() {
        int screen[] = getScreenSize();
        return screen[0];
    }
    /**
     * 补码到原码的转化函数
     * **/
    public static int toData(int result) {
        // TODO Auto-generated method stub
        //int result=Integer.parseInt(str, 16);
        if(result>=128){
            //取出每一位的值
            int wei[]=new int[7];
            for(int i=0;i<7;i++){
                int x = 0x1 & (result >> i);
                wei[i]=x;
            }

            //取反
            for(int i=0;i<7;i++){
                if(wei[i]==1){
                    wei[i]=0;
                }else{
                    wei[i]=1;
                }
            }

            //各位相加后再加一
            result=0;
            for(int i=0;i<7;i++){
                result=result+(int)(wei[i]*(Math.pow(2, i)));
            }
            result=1+result;
            result=0-result;
        }
        return result;
    }


    /**
     * @Title: getScreenHeight
     * @Description: 获取手机屏幕的高度
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    public static int getScreenHeight() {
        int screen[] = getScreenSize();
        return screen[1];
    }

    /**
     * 根据手机分辨率将dp转为px单位
     */
    public static int dip2px(float dpValue) {
        final float scale = BaseApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = BaseApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
