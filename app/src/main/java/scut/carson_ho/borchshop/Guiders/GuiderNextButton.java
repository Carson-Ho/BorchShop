package scut.carson_ho.borchshop.Guiders;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import scut.carson_ho.borchshop.Interfaces.NextButtonClickListener;
import scut.carson_ho.borchshop.R;

/**
 * 用于购机向导里下一步的按钮，通过传入一个intent来实现不同的跳转
 */

public class GuiderNextButton extends Button implements View.OnClickListener {
    private Intent intent;
    private GudierActivity activity;
    private ArrayList<TextView> tv_list;
    private ArrayList<String> checkDefaultStrings;
    private NextButtonClickListener clickListener;

    public GuiderNextButton(Context activity) {
        super(activity);
        init();
    }

    public GuiderNextButton(Context activity, AttributeSet attrs) {
        super(activity, attrs);
        init();
    }

    public GuiderNextButton(Context activity, AttributeSet attrs, int defStyleAttr) {
        super(activity, attrs, defStyleAttr);
    }

    private void init(){
        setOnClickListener(this);
        checkDefaultStrings = new ArrayList<>();
    }

    public void setActivity(GudierActivity activity){
        this.activity = activity;

    }



    @Override
    public void onClick(View v) {
        if (clickListener != null){
            clickListener.onNextButtonClick();
        }
        intent = activity.pushData();
        if (activity != null && intent != null){
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.in_from_right, R.anim.fade_out);
        }else {
            System.out.println("activity or intent is null!");
        }
    }

    //在这里传入TextView数组并为它们绑定自定义TextWatcher监听内容里面的改变
    public void AddListeningEditTexts(ArrayList<TextView> editTexts){
        GuiderNextButton.CheckBlank_TextWatcher mCheckBlank_TextWatcher = new GuiderNextButton.CheckBlank_TextWatcher(this);
        setEnabled(false);
        tv_list = editTexts;
        for (int i = 0; i < tv_list.size(); i++){
            tv_list.get(i).addTextChangedListener(mCheckBlank_TextWatcher);
        }

    }

    public void setCheckDefaultStrings(ArrayList<String> checkDefaultStrings) {
        this.checkDefaultStrings = checkDefaultStrings;
    }

    private boolean isDefaultString (ArrayList<String> strings, String text){
        for(int i = 0; i < strings.size(); i++ ){
            if (strings.get(i).equals(text)){
                return true;
            }
        }
        return false;
    }
    //重写TextWatcher，监听操作写在里面
    private class CheckBlank_TextWatcher implements TextWatcher {
        private GuiderNextButton mButtonCheckBlank;

        public  CheckBlank_TextWatcher(GuiderNextButton buttonCheckBlank){
            mButtonCheckBlank = buttonCheckBlank;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            for (int i = 0; i < tv_list.size(); i++){
                //判断列表里面的EditText是否为空，是空就return，当所有都不是空的时候button就可以按下
                if (TextUtils.isEmpty(tv_list.get(i).getText())){
                    mButtonCheckBlank.setEnabled(false);
                    return;
                }
            }
            mButtonCheckBlank.setEnabled(true);
        }
    }

    public void setClickListener(NextButtonClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
