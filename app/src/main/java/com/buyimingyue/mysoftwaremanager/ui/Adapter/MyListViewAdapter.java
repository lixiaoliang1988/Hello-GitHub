package com.buyimingyue.mysoftwaremanager.ui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.buyimingyue.mysoftwaremanager.MyApplication;
import com.buyimingyue.mysoftwaremanager.R;

import java.util.List;

/**
 * Created by lixiaoliang on 2016/7/18.
 * Description:
 */
public class MyListViewAdapter extends BaseAdapter {
    private List<ApplicationInfo> applicationInfos;
    private Context context;
    private PackageManager pm;
    public MyListViewAdapter(Context context,List<ApplicationInfo> applicationInfos) {
        this.context = context;
        pm = MyApplication.getInstance().getPackageManager();
        this.applicationInfos = applicationInfos;
    }
    public void  setData(List<ApplicationInfo> applicationInfos){
        this.applicationInfos = applicationInfos;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return applicationInfos==null?0:applicationInfos.size();
    }
    @Override
    public Object getItem(int i) {
        return applicationInfos==null?null:applicationInfos.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(final int i, View container, ViewGroup viewGroup) {
        ViewHolder viewHolder =null;
        if(container==null){
            viewHolder = new ViewHolder();
            container = View.inflate(context, R.layout.main_listview_item,null);
            viewHolder.imageViewIcon = (ImageView) container.findViewById(R.id.image_icon);
            viewHolder.textViewName = (TextView) container.findViewById(R.id.tv_name);
            viewHolder.textViewContent = (TextView) container.findViewById(R.id.tv_content);
            viewHolder.button_Uninstall = (Button) container.findViewById(R.id.btn_uninstall);
            viewHolder.button_Open = (Button) container.findViewById(R.id.btn_open);
            container.setTag(viewHolder);
        }else viewHolder = (ViewHolder) container.getTag();
        ApplicationInfo appInfo = applicationInfos.get(i);
            Drawable drawable = appInfo.loadIcon(pm);
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            viewHolder.imageViewIcon.setImageDrawable(drawable);
            viewHolder.textViewName.setText(appInfo.loadLabel(pm).toString());
            viewHolder.textViewContent.setText(appInfo.packageName);
            viewHolder.button_Uninstall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String packageName =  applicationInfos.get(i).packageName;
                    Log.i("msg","msg:"+packageName);
                    Uri uri = Uri.fromParts("package",packageName,null);
                    Intent  intent = new Intent(Intent.ACTION_DELETE,uri);
                    context.startActivity(intent);
                }
            });
            viewHolder.button_Open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = applicationInfos.get(i).packageName;
                    Intent intent = pm.getLaunchIntentForPackage(name);
                    context.startActivity(intent);
                }
            });
        return container;
    }
    public class ViewHolder{
        private TextView textViewName;
        private TextView textViewContent;
        private ImageView imageViewIcon;
        private Button button_Uninstall;
        private Button button_Open;
    }
}
