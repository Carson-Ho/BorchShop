package scut.carson_ho.borchshop.Guiders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import scut.carson_ho.borchshop.MachineToShopActivity;
import scut.carson_ho.borchshop.R;

public class GuiderActivity5 extends GudierActivity {
    private GuiderNextButton btn_next;
    private GuiderJumpButton btn_jump;
    private GuiderBackbutton btn_back;
    private EditText et_Guider5Name,et_Guider5Phone,et_Guider5Company;
    private ProductSearchParms productSearchParms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guider5);
        init();
    }
    private void init(){
        btn_next = (GuiderNextButton) findViewById(R.id.btn_Guider_Next);
        btn_next.setActivity(this);
        btn_jump = (GuiderJumpButton) findViewById(R.id.btn_Guider_Jump);
        btn_jump.setIntent(this,new Intent(this, MachineToShopActivity.class));
        btn_back = (GuiderBackbutton) findViewById(R.id.btn_Guider_back);
        btn_back.setActivity(this);

        et_Guider5Name = (EditText) findViewById(R.id.et_Guider5Name);
        et_Guider5Phone = (EditText) findViewById(R.id.et_Guider5Phone);
        et_Guider5Company = (EditText) findViewById(R.id.et_Guider5Company);

        ArrayList<TextView> defaultTextViews = new ArrayList<>();
        defaultTextViews.add(et_Guider5Name);
        defaultTextViews.add(et_Guider5Phone);
        defaultTextViews.add(et_Guider5Company);
        btn_next.AddListeningEditTexts(defaultTextViews);

        productSearchParms = (ProductSearchParms) getIntent().getSerializableExtra("productSearchParms");
//        System.out.println(productSearchParms.getPowerType());

    }

    @Override
    public Intent pushData() {
        Intent intent = new Intent(this,MachineToShopActivity.class);
        productSearchParms.setName(et_Guider5Name.getText().toString());
        productSearchParms.setPowerType(et_Guider5Phone.getText().toString());
        productSearchParms.setCompany(et_Guider5Company.getText().toString());
        System.out.println("类型："+productSearchParms.getProductType());
        System.out.println("原料："+productSearchParms.getMaterial());
        System.out.println("重量："+productSearchParms.getProductWeight());
        System.out.println("长："+productSearchParms.getModuleLength());
        System.out.println("宽："+productSearchParms.getModuleWidth());
        System.out.println("高："+productSearchParms.getModuleHeight());
        System.out.println("顶出方式："+productSearchParms.getEjector());
        System.out.println("定位环："+productSearchParms.getLocatingRing());
        System.out.println("螺杆类型："+productSearchParms.getScrewType());
        System.out.println("动力类型："+productSearchParms.getPowerType());
        System.out.println("姓名："+productSearchParms.getName());
        System.out.println("年龄："+productSearchParms.getPhoneNumber());
        System.out.println("公司："+productSearchParms.getCompany());
        return intent;
    }
}
