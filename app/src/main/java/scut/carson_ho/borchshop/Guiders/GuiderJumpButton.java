package scut.carson_ho.borchshop.Guiders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import scut.carson_ho.borchshop.R;

/**
 * 用于跳过购机向导的Button，通过传入一个intent来实现不同的跳转
 */

public class GuiderJumpButton extends TextView implements View.OnClickListener{
    private Intent intent;
    private Activity activity;
    public GuiderJumpButton(Context activity) {
        super(activity);
        init();
    }

    public GuiderJumpButton(Context activity, AttributeSet attrs) {
        super(activity, attrs);
        init();
    }

    public GuiderJumpButton(Context activity, AttributeSet attrs, int defStyleAttr) {
        super(activity, attrs, defStyleAttr);
    }


    public void setIntent(Activity activity,Intent intent){
        this.activity = activity;
        this.intent = intent;
    }

    private void init(){
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (activity != null && intent != null){
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.in_from_right, R.anim.fade_out);
        }else {
            System.out.println("activity or intent is null!");
        }
    }
}
