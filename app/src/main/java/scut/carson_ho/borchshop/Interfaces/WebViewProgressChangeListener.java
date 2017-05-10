package scut.carson_ho.borchshop.Interfaces;

/**
 * 用于在WebviewUtility的进度改变时回调
 * 因为已经在WebviewUtility里面绑定了加载进度条，所以使用这个来绑定ProgressDialog
 */

public interface WebViewProgressChangeListener {
    public void onWebViewProgressChange(int progress);
}
