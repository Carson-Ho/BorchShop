package scut.carson_ho.borchshop.Guiders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import scut.carson_ho.borchshop.R;
import scut.carson_ho.borchshop.WebActivity;

public class GuiderActivity3 extends GudierActivity {
    private GuiderNextButton btn_next;
    private GuiderJumpButton btn_jump;
    private GuiderBackbutton btn_back;
    private EditText et_Guider3Length,et_Guider3Width,et_Guider3Height;
    ProductSearchParms productSearchParms;
    RadioGroup rg_Method,rg_Ring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guider3);
        init();
    }

    private void init(){
        btn_next = (GuiderNextButton) findViewById(R.id.btn_Guider_Next);
        btn_next.setActivity(this);
        btn_jump = (GuiderJumpButton) findViewById(R.id.btn_Guider_Jump);
        btn_jump.setIntent(this,new Intent(this, WebActivity.class));
        btn_back = (GuiderBackbutton) findViewById(R.id.btn_Guider_back);
        btn_back.setActivity(this);

        et_Guider3Length = (EditText) findViewById(R.id.et_Guider3Length);
        et_Guider3Width = (EditText) findViewById(R.id.et_Guider3Width);
        et_Guider3Height = (EditText) findViewById(R.id.et_Guider3Height);

        ArrayList<TextView> defaultTextViews = new ArrayList<>();
        defaultTextViews.add(et_Guider3Length);
        defaultTextViews.add(et_Guider3Width);
        defaultTextViews.add(et_Guider3Height);

        btn_next.AddListeningEditTexts(defaultTextViews);
        Intent intent = getIntent();
        productSearchParms = (ProductSearchParms) intent.getSerializableExtra("productSearchParms");


        rg_Method = (RadioGroup) findViewById(R.id.rg_Method);
        rg_Method.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_guider3_method_out:
                        productSearchParms.setEjector("ejector");
                        break;
                    case R.id.btn_guider3_method_pull:
                        productSearchParms.setEjector("stretching");
                        break;
                }
            }
        });
        rg_Ring = (RadioGroup) findViewById(R.id.rg_Ring);
        rg_Ring.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_guider3_Ring1:
                        productSearchParms.setLocatingRing("standard");
                        break;
                    case R.id.btn_guider3_Ring2:
                        productSearchParms.setLocatingRing("nonStandard");
                        break;
                }
            }
        });
    }

    @Override
    public Intent pushData() {
        Intent intent = new Intent(this,GuiderActivity4.class);
        productSearchParms.setModuleLength(Float.parseFloat(et_Guider3Length.getText().toString()));
        productSearchParms.setModuleWidth(Float.parseFloat(et_Guider3Width.getText().toString()));
        productSearchParms.setModuleHeight(Float.parseFloat(et_Guider3Height.getText().toString()));

        intent.putExtra("productSearchParms",productSearchParms);
        return intent;
    }
}
