package scut.carson_ho.borchshop;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

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
        webview.loadMessageUrl("http://shop.borche.cn/mobile/");
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

