package scut.carson_ho.borchshop.Guiders;

import android.content.Intent;
import android.os.Bundle;

import scut.carson_ho.borchshop.R;
import scut.carson_ho.borchshop.WebActivity;

public class GuiderActivity1 extends GudierActivity {
    private GuiderNextButton btn_start;
    private GuiderJumpButton btn_jump;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guider1);
        init();
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
}
