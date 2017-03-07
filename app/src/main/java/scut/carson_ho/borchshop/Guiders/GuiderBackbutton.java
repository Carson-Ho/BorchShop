package scut.carson_ho.borchshop.Guiders;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import scut.carson_ho.borchshop.R;

/**
 * Created by yany on 2017/3/5.
 */

public class GuiderBackbutton extends Button implements View.OnClickListener{
    private Activity activity;
    public GuiderBackbutton(Context context) {
        super(context);
        init();
    }

    public GuiderBackbutton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuiderBackbutton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private void init(){
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (activity != null) {
            activity.finish();
            activity.overridePendingTransition(R.anim.fade_in, R.anim.out_from_right);
        }
        System.out.println("back");
    }
}
