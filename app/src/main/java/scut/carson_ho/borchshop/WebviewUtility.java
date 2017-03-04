package scut.carson_ho.borchshop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;
import android.widget.ProgressBar;

/**
 * Created by Carson_Ho on 17/2/28.
 * @Description: webview工具类
 */
public class WebviewUtility extends WebView {

    private ProgressBar mProgressBar;


    //在构造函数里进行初始化
    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public WebviewUtility(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initContext(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public WebviewUtility(Context context, AttributeSet attrs) {
        super(context, attrs);
        initContext(context);
    }

    /**
     * @param context
     */
    public WebviewUtility(Context context) {
        super(context);
        initContext(context);
    }

    /**
     * @Title: initContext
     * @Description: 初始化context
     * @param context
     * @return void
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")

    // 初始化
    // 对WebView进行设置
    private void initContext(Context context) {
        requestFocus();
        setInitialScale(39);
        //支持与JS交互
        getSettings().setJavaScriptEnabled(true);
        //支持通过Js打开新窗口
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        //设置自适应屏幕
        getSettings().setUseWideViewPort(true);//将图片调整到适合Webview的大小
        getSettings().setLoadWithOverviewMode(true);//缩放至屏幕大小

        //设置是否开启DOM存储API权限
        getSettings().setDomStorageEnabled(true);

        //设置开启数据库存储API权限
        getSettings().setDatabaseEnabled(true);


        // 只有17及以上才可以
        if (android.os.Build.VERSION.SDK_INT >= 17)
            //设置WebView是否通过手势触发播放媒体，默认是true，需要手势触发。
            getSettings().setMediaPlaybackRequiresUserGesture(false);

        String cacheDirPath = SdcardConfig.WEB_CACHE_FOLDER;
        //设置当前Application缓存文件路径
        //设置网页缓存路径:数据库+Application
        getSettings().setDatabasePath(cacheDirPath);
        getSettings().setAppCachePath(cacheDirPath);
        //设置Application缓存权限
        getSettings().setAppCacheEnabled(true);

        //HTTP请求头部用来标识客户端信息的字符串-Set用来设置的,Get用来获取的
        //为了建立手机客户端的信息数据库，需要从手机的http请求中取到这一字符串
        getSettings().setUserAgentString(getSettings().getUserAgentString() + "  Flygift/android");

    }

    /**
     * @Title: loadMessageUrl
     * @Description: 加载网页url
     * @param url
     * @return void
     */


    // 点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器

    public void loadMessageUrl(String url) {
        super.loadUrl(url);
        setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loadUrl(url);
                //loadDataWithBaseURL("about:blank", url, "text/html", "utf-8", null);
                return true;
            }
        });
    }

    /**
     * @Title: addProgressBar
     * @Description: 添加进度条
     * @return void
     */
    @SuppressWarnings("deprecation")
    public void addProgressBar() {
        mProgressBar = new ProgressBar(getContext(), null,
                android.R.attr.progressBarStyleHorizontal);
        mProgressBar.setLayoutParams(new AbsoluteLayout.LayoutParams(
                AbsoluteLayout.LayoutParams.MATCH_PARENT, ScreenUtil.dip2px(1), 0, 0));
        mProgressBar.setProgressDrawable(getContext().getResources()
                .getDrawable(R.drawable.web_loading));
        addView(mProgressBar);

        setWebChromeClient(new WebChromeClient() {

            //支持javascript的确认框 confirm
            @Override
            public boolean onJsConfirm(WebView view, String url,
                                       String message, final android.webkit.JsResult result) {
                return true;
            }
            //支持javascript的警告框 alert
            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     final android.webkit.JsResult result) {
                return false;
            }

            //获取网页进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(GONE);
                } else {
                    if (mProgressBar.getVisibility() == GONE)
                        mProgressBar.setVisibility(VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    /**
     * @Title: destroyWebView
     * @Description: 回收webview
     * @return void
     */
    public void destroyWebView() {
        clearCache(true);
        clearHistory();
    }

}
