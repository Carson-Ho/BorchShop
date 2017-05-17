package scut.carson_ho.borchshop.Guiders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import scut.carson_ho.borchshop.Initialization.BaseApplication;
import scut.carson_ho.borchshop.Interfaces.NextButtonClickListener;
import scut.carson_ho.borchshop.R;
import scut.carson_ho.borchshop.Web.WebActivity;
import scut.carson_ho.borchshop.Interfaces.WebViewProgressChangeListener;
import scut.carson_ho.borchshop.Web.WebviewEntity;

public class GuiderActivity5 extends GudierActivity implements NextButtonClickListener,WebViewProgressChangeListener{
    private GuiderNextButton btn_next;
    private GuiderJumpButton btn_jump;
    private GuiderBackbutton btn_back;
    private EditText et_Guider5Name,et_Guider5Phone,et_Guider5Company;
    private ProductSearchParms productSearchParms;
    public CustomProgressDialog customProgressDialog;
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
        btn_jump.setIntent(this,new Intent(this, WebActivity.class));
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
        btn_next.setClickListener(this);

        productSearchParms = (ProductSearchParms) getIntent().getSerializableExtra("productSearchParms");
//        System.out.println(productSearchParms.getPowerType());
        //初始化Dialog
        customProgressDialog = new CustomProgressDialog(this);
        customProgressDialog.setMessage("加载中");

    }

    @Override
    public Intent pushData() {
        Intent intent = new Intent(this,WebActivity.class);
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
        System.out.println("手机号码："+productSearchParms.getPhoneNumber());
        System.out.println("公司："+productSearchParms.getCompany());
        return null;
    }

    @Override
    public void onNextButtonClick() {

        WebviewEntity webView = BaseApplication.getmWebView();
        webView.setProgressChangeListener(this);
        webView.loadMessageUrl("http://shop.borche.cn/mobile/m_search/" +
                "list?isSelectMC=1&productType= \"" + productSearchParms.getProductType()
                + "\"&material=" + productSearchParms.getMaterial()
                + " & productWeight = " + productSearchParms.getProductWeight()
                + "& moduleLength = " + productSearchParms.getModuleLength()
                + "& moduleHeight = " + productSearchParms.getModuleHeight()
                + "& area = " + (productSearchParms.getProductWeight() * productSearchParms.getModuleLength() * productSearchParms.getModuleHeight())
                + "& ejector =" + productSearchParms.getEjector()
                + "& locatingRing =" + productSearchParms.getLocatingRing()
                + "& screwType = " + productSearchParms.getScrewType()
                + "& powerType = " + productSearchParms.getPowerType()
                + "& name =" + productSearchParms.getName()
                + "& phoneNumber = " + productSearchParms.getPhoneNumber()
                + "& company = " + productSearchParms.getCompany());

        // 测试的:http://shop.borche.cn/mobile/m_search/list?isSelectMC=1&productType= "建筑材料"&material=PET&productWeight=1&moduleLength=1&moduleHeight=1&area=1&ejector=ejector&locatingRing=standard&screwType=screw_A&powerType=oilPressure&name=1&phoneNumber=1&company=1

        customProgressDialog.show();
    }

    @Override
    public void onWebViewProgressChange(int progress) {
        customProgressDialog.setProgress(progress);
        if (progress == 100){
            Intent intent = new Intent(this,WebActivity.class);
            startActivity(intent);
            //关闭Dialog，不用cancel方法，cancel方法会回调Listener
            customProgressDialog.dismiss();
        }
    }
}
