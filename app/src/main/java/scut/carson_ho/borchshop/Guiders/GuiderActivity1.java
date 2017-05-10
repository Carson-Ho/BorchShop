package scut.carson_ho.borchshop.Guiders;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import scut.carson_ho.borchshop.Initialization.BaseApplication;
import scut.carson_ho.borchshop.R;
import scut.carson_ho.borchshop.Web.WebActivity;

public class GuiderActivity1 extends GudierActivity {
    private GuiderNextButton btn_start;
    private GuiderJumpButton btn_jump;
    private long mExitTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guider1);
        init();
        BaseApplication.activitiesClearTop();

    }

    private void init(){
        btn_start = (GuiderNextButton) findViewById(R.id.btn_Guider_Start);
        btn_start.setActivity(this);
        btn_jump = (GuiderJumpButton) findViewById(R.id.btn_Guider_Jump);
        btn_jump.setIntent(this,new Intent(this, WebActivity.class));

    }


    @Override
    public Intent pushData() {
        return new Intent(this,GuiderActivity2.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestory");
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序

                //退出APP
                finish();
                overridePendingTransition(R.anim.anim_null, R.anim.scale_out);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
