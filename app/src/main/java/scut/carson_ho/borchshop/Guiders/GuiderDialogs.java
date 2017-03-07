package scut.carson_ho.borchshop.Guiders;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yany on 2017/3/7.
 */

//对话框大量重用类
public class GuiderDialogs {


    //重用选择对话框使用入口，调用此方法，可以实现TextView监听点击，TextView显示效果
    // 输入三个参数：上下文，绑定后的TextView列表，选择内容列表数组。
    public GuiderDialogs(final Context context, List<TextView> textViews, List<String[]> selectedDataList){
        for (int i = 0;i<textViews.size();i++){
            textViews.get(i).setOnClickListener(new DialogOnClickListener(textViews.get(i),selectedDataList.get(i),context));
        }
    }
    //重用选择对话框使用入口，调用此方法，可以实现View监听点击，TextView显示效果
    // 输入四个参数：上下文，绑定后的TextView列表，选择内容列表数组。
    public GuiderDialogs(final Context context, List<TextView> textViews, List<View> views, List<String[]> selectedDataList){
        for (int i = 0;i<textViews.size();i++){
            views.get(i).setOnClickListener(new DialogOnClickListener(textViews.get(i),selectedDataList.get(i),context));
        }
    }

    //重写点击监听器，让它可以传入TextView,List<String>等参数
    private class DialogOnClickListener implements View.OnClickListener{
        private TextView textView;
        private String[] selectedList;
        private Context mcontext;
        public DialogOnClickListener(TextView tv,String[] sList,Context context){
            textView = tv;
            selectedList = sList;
            mcontext = context;
        }

        //设置点击事件，点击TextView后弹出Dialog
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
            builder.setTitle("选择一个参数");
            //    设置一个下拉的列表选择项
            builder.setItems(selectedList, new DialogSelectedItemOnClickListener(textView,selectedList));
            builder.show();

        }
    }


    //重写Dialog的点击监听器，让它可以传入TextView,List<String>等参数
    private class DialogSelectedItemOnClickListener implements DialogInterface.OnClickListener{
        private TextView ctv;
        private String[] selectedList;
        public DialogSelectedItemOnClickListener(TextView ctextView, String[] sList){
            ctv = ctextView;
            selectedList = sList;
        }
        //设置点击事件
        @Override
        public void onClick(DialogInterface dialog, int which) {
            ctv.setTextColor(Color.rgb(51,51,51));
            ctv.setText(selectedList[which]);
        }
    }
}