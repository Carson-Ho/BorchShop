package scut.carson_ho.borchshop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import scut.carson_ho.borchshop.Guiders.GuiderActivity1;

/**
 * Created by Carson_Ho on 17/2/28.
 */
public class WebActivity extends Activity {

    //声明的Webview子类
    private WebviewUtility webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_shop);
        // 初始化Webview
        initView();

    }

    private void initView() {
        //绑定布局组件,自动实例化
        webview=(WebviewUtility) findViewById(R.id.webview);
        // 传入对应的url
        //        webview.loadMessageUrl("http://shop.borche.cn/mobile/");// 首页

        // webview.loadMessageUrl("http://121.40.100.57/mobile/m_search/list?isSelectMC=1&productType= \"建筑材料\"&material=PET&productWeight=1&moduleLength=1&moduleHeight=1&area=1&ejector=ejector&locatingRing=standard&screwType=screw_A&powerType=oilPressure&name=1&phoneNumber=1&company=1");//注塑系统
        // 选型系统到商城


        // 商城到选型系统
        webview.loadMessageUrl("http://121.40.100.57/mobile");// 首页URL


        // 复写WebViewClient类的shouldOverrideUrlLoading方法
        webview.setWebViewClient(new WebViewClient() {
                                     @Override
                                     public boolean shouldOverrideUrlLoading(WebView view, String url) {

                                         // 步骤2：根据协议的参数，判断是否是所需要的url
                                         // 根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                                         // 首页的JS传入进来的 url = 'xmg://backToLoaclPage'（同时也是约定好的需要拦截的）

                                         Uri uri = Uri.parse(url);
                                         // 如果url的协议 = 预先约定的 js 协议
                                         // 就解析往下解析参数
                                         if (uri.getScheme().equals("xmg")) {

                                             // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                                             // 所以拦截url,下面JS开始调用Android需要的方法
                                             if (uri.getAuthority().equals("backToLoaclPage")) {

                                                 //  步骤3：
                                                 // 执行JS所需要调用的逻辑
                                                 System.out.println("js调用了Android的方法");
                                                 Intent localIntent = new Intent();
                                                 localIntent.setClass(WebActivity.this, GuiderActivity1.class);
                                                 startActivity(localIntent);
                                                 finish();

                                             }

                                             return true;
                                         }
                                         return super.shouldOverrideUrlLoading(view, url);
                                     }
                                 }
        );
    }



    //控制网页回退,而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }




}

