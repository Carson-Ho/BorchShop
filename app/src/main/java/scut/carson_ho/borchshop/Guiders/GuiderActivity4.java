package scut.carson_ho.borchshop.Guiders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

import scut.carson_ho.borchshop.R;
import scut.carson_ho.borchshop.WebActivity;

public class GuiderActivity4 extends GudierActivity {
    private GuiderNextButton btn_next;
    private GuiderJumpButton btn_jump;
    private GuiderBackbutton btn_back;
    private ProductSearchParms productSearchParms;
    private RadioGroup rg_screwAB,rg_Power12,rg_Power34;
    private MutilayoutRadioGroup rg_screw,rg_Power;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guider4);
        init();

    }


    private void init(){
        btn_next = (GuiderNextButton) findViewById(R.id.btn_Guider_Next);
        btn_next.setActivity(this);
        btn_jump = (GuiderJumpButton) findViewById(R.id.btn_Guider_Jump);
        btn_jump.setIntent(this,new Intent(this, WebActivity.class));
        btn_back = (GuiderBackbutton) findViewById(R.id.btn_Guider_back);
        btn_back.setActivity(this);

        productSearchParms = (ProductSearchParms) getIntent().getSerializableExtra("productSearchParms");
        System.out.println("Ring and method"+productSearchParms.getLocatingRing()+productSearchParms.getEjector());

        rg_screw = (MutilayoutRadioGroup) findViewById(R.id.rg_Screw);
        rg_screw.setOnCheckedChangeListener(new MutilayoutRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MutilayoutRadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_guider4_ScrewA:
                        productSearchParms.setScrewType("screw_A");
                        break;
                    case R.id.btn_guider4_ScrewB:
                        productSearchParms.setScrewType("screw_B");
                        break;
                    case R.id.btn_guider4_ScrewC:
                        productSearchParms.setScrewType("screw_C");
                        break;
                }
            }

        });

        rg_Power = (MutilayoutRadioGroup) findViewById(R.id.rg_Power);
        rg_Power.setOnCheckedChangeListener(new MutilayoutRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MutilayoutRadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_guider4_Power1:
                        productSearchParms.setPowerType("oilPressure");
                        break;
                    case R.id.btn_guider4_Power2:
                        productSearchParms.setPowerType("hydPressure");
                        break;
                    case R.id.btn_guider4_Power3:
                        productSearchParms.setPowerType("allElectric");
                        break;
                    case R.id.btn_guider4_Power4:
                        productSearchParms.setPowerType("blend");
                        break;
                }
            }
        });
    }

    @Override
    public Intent pushData() {
        Intent intent = new Intent(this,GuiderActivity5.class);
        intent.putExtra("productSearchParms",productSearchParms);
        return intent;
    }
}
