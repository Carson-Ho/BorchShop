package scut.carson_ho.borchshop.Interfaces;

/**
 * 由于前面NextButton已经封装，想要在选型系统最后一个按钮加入点击触发事件只能另外抽出一个接口了
 */

public interface NextButtonClickListener {
    public void onNextButtonClick();
}
