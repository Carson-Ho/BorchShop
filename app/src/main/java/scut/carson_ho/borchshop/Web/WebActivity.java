package scut.carson_ho.borchshop.Web;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import scut.carson_ho.borchshop.Guiders.GuiderActivity1;
import scut.carson_ho.borchshop.Initialization.BaseApplication;
import scut.carson_ho.borchshop.R;

/**
 * 用于显示WebView的Activity
 */
public class WebActivity extends Activity {

    //声明的Webview子类
    private WebviewEntity webview;
    //声明父View
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_shop);
        // 初始化Webview
        initView();

    }

    private void initView() {
        //绑定布局组件,自动实例化
//        webview=(WebviewUtility) findViewById(R.id.webview);
        // 传入对应的url
        //        webview.loadMessageUrl("http://shop.borche.cn/mobile/");// 首页

        // webview.loadMessageUrl("http://121.40.100.57/mobile/m_search/list?isSelectMC=1&productType= \"建筑材料\"&material=PET&productWeight=1&moduleLength=1&moduleHeight=1&area=1&ejector=ejector&locatingRing=standard&screwType=screw_A&powerType=oilPressure&name=1&phoneNumber=1&company=1");//注塑系统
        // 选型系统到商城

        //从BaseApplication里获取WebView
        webview = BaseApplication.getmWebView();

        //加入WebView
        linearLayout = (LinearLayout) findViewById(R.id.ly_web);
        webview = BaseApplication.getmWebView();
        webview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));



        // 商城到选型系统
//        webview.loadMessageUrl("http://121.40.100.57/mobile");// 首页URL


        // 这里用新的WebViewClient去取代旧的，就是因为要加入拦截JS
        // 复写WebViewClient类的shouldOverrideUrlLoading方法,以拦截JS和出错回调
        webview.setWebViewClient(new WebViewClient() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                if (!request.getUrl().toString().equals("xmg://backToLoaclPage")){
                    webview.loadMessageUrl("file:///android_asset/error_disconnect.html");
                }
                super.onReceivedError(view, request, error);
            }

            //API23前调用
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                if (!failingUrl.equals("xmg://backToLoaclPage")){
                    webview.loadMessageUrl("file:///android_asset/error_disconnect.html");
                }
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
            //API版本21才执行，用于根据Http状态码来判断是否跳转到错误页
            //看源码发现这个方法是http状态码大于等于400才会被调用，这样的话一些400-499的图片加载出错等都会调用
            //所以直接设为服务器出错才跳到错误页
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                if (!request.getUrl().toString().equals("xmg://backToLoaclPage")) {
                    //服务器出错
                    if (errorResponse.getStatusCode() >= 500) {
                        webview.loadMessageUrl("file:///android_asset/error_http.html");
                    }
                }
                super.onReceivedHttpError(view, request, errorResponse);
            }

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
                    }
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        }
        );

        linearLayout.addView(webview);
    }



    //控制网页回退,而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            if (webview.canGoBack()) {
                webview.goBack();// 返回前一个页面
                return true;
            }
        }


        return super.onKeyDown(keyCode, event);
    }

    //防止再次进入时候重复addView，所以当此Activity退出时要RemoveView，并且设置启动模式为SingleTask
    @Override
    protected void onDestroy() {
        super.onDestroy();
        linearLayout.removeView(webview);
        BaseApplication.setmWebView(webview);
        webview = null;
    }
}

