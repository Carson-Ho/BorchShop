package scut.carson_ho.borchshop.Guiders;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import scut.carson_ho.borchshop.R;
import scut.carson_ho.borchshop.WebActivity;

public class GuiderActivity2 extends GudierActivity {
    private GuiderNextButton btn_next;
    private GuiderJumpButton btn_jump;
    private GuiderBackbutton btn_back;
    private TextView tv_Guider2TypeContent, tv_Guider2MaterialContent;
    private ImageView iv_Guider2Type, iv_Guider2Material;
    private EditText et_Guider2Weight;
    private ProductSearchParms productSearchParms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guider2);
        init();
    }

    private void init(){
        btn_next = (GuiderNextButton) findViewById(R.id.btn_Guider_Next);
        btn_next.setActivity(this);
        btn_jump = (GuiderJumpButton) findViewById(R.id.btn_Guider_Jump);
        btn_jump.setIntent(this,new Intent(this, WebActivity.class));
        btn_back = (GuiderBackbutton) findViewById(R.id.btn_Guider_back);
        btn_back.setActivity(this);

        tv_Guider2TypeContent = (TextView) findViewById(R.id.tv_Guider2TypeContent);
        tv_Guider2MaterialContent = (TextView) findViewById(R.id.tv_Guider2MaterialContent);
        iv_Guider2Type = (ImageView) findViewById(R.id.iv_Guider2Type);
        iv_Guider2Material = (ImageView) findViewById(R.id.iv_Guider2Material);
        et_Guider2Weight = (EditText) findViewById(R.id.et_Guider2Weight);

        ArrayList<TextView> textViews = new ArrayList<>();
        textViews.add(tv_Guider2TypeContent);
        textViews.add(tv_Guider2MaterialContent);
        ArrayList<View> views = new ArrayList<>();
        views.add(iv_Guider2Type);
        views.add(iv_Guider2Material);
        ArrayList<String[]> strings = new ArrayList<>();
        Resources resources = getResources();
        strings.add(resources.getStringArray(R.array.TypeArray));
        strings.add(resources.getStringArray(R.array.MaterialArray));
        GuiderDialogs guiderDialogs = new GuiderDialogs(this,textViews,views,strings);

        ArrayList<String> defaultStrings = new ArrayList<>();
        defaultStrings.add(resources.getString(R.string.Guider2TypeEditTextDefault));
        defaultStrings.add(resources.getString(R.string.Guider2MaterialEditTextDefault));
        btn_next.setCheckDefaultStrings(defaultStrings);
        ArrayList<TextView> defaultTextViews = new ArrayList<>();
        defaultTextViews.add(tv_Guider2TypeContent);
        defaultTextViews.add(tv_Guider2MaterialContent);
        defaultTextViews.add(et_Guider2Weight);
        btn_next.AddListeningEditTexts(defaultTextViews);

        productSearchParms = new ProductSearchParms();
    }

    @Override
    public Intent pushData() {
        Intent intent = new Intent(this,GuiderActivity3.class);
        productSearchParms.setProductType(tv_Guider2TypeContent.getText().toString());
        productSearchParms.setMaterial(tv_Guider2MaterialContent.getText().toString());
        productSearchParms.setProductWeight(Float.parseFloat(et_Guider2Weight.getText().toString()));
        intent.putExtra("productSearchParms",productSearchParms);
        return intent;
    }
}
