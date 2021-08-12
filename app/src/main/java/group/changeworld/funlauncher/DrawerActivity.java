package group.changeworld.funlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import group.changeworld.funlauncher.util.ToastUtil;

public class DrawerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        //初始化Toast的主要作用是，在点击按钮是可以先立刻消除上一个toast
        //Toast toast=Toast.makeText(getApplicationContext(), "", 0);;
        //Toast.makeText(DrawerActivity.this,"drawer",Toast.LENGTH_SHORT).show();
        //ToastUtil.show(this,"drawer");

        loadApps();
        loadListView();
        addClickListener();
    }
    //获取app
    private PackageManager pm;
    private List<AppItem> appitems;
    private void loadApps(){
        pm=getPackageManager();
        appitems=new ArrayList<AppItem>();

        Intent i=new Intent(Intent.ACTION_MAIN,null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> allActivities=pm.queryIntentActivities(i,0);
        for(ResolveInfo ri:allActivities){
            AppItem appItem=new AppItem();
            appItem.label=ri.loadLabel(pm);
            appItem.name=ri.activityInfo.packageName;
            appItem.icon=ri.activityInfo.loadIcon(pm);
            appitems.add(appItem);
        }

        //ToastUtil.show(this,"22222");
    }

    //
    private ListView list;
    private void loadListView(){
        list = (ListView)findViewById(R.id.drawer_list);

        ArrayAdapter<AppItem> mAdapter=new ArrayAdapter<AppItem>(this,R.layout.activity_drawer_list_item,appitems){
          @Override
            public View getView(int position, View convertView, ViewGroup parent){
              /**
               * 获取inflate实例的三种方式
               * 1.LayoutInflater inflater = getLayoutInflater(); //调用Activity的getLayoutInflater()
               * 2.LayoutInflater inflater = LayoutInflater.from(context);
               * 3.LayoutInflater localinflater =(LayoutInflater)context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
               * 研究源码可以看到 getLayoutInflater() 中调用了 LayoutInflater.from(context)，
               * 而LayoutInflater.from(context) 中又调用了(LayoutInflater)context.getSystemService
               * 所以可以知道，三种调用方式本质是没有区别的。
               * */


              if(convertView == null){
                  convertView = getLayoutInflater().inflate(R.layout.activity_drawer_list_item, null);
              }

              ImageView appIcon=(ImageView)convertView.findViewById(R.id.item_icon);
              appIcon.setImageDrawable(appitems.get(position).icon);
              TextView appLabel=(TextView)convertView.findViewById(R.id.item_name);
              appLabel.setText(appitems.get(position).label);
              TextView appPackageName=(TextView)convertView.findViewById(R.id.item_package_name);
              appPackageName.setText(appitems.get(position).name);

              return convertView;

          }
        };
        list.setAdapter(mAdapter);

        //ToastUtil.show(this,"3333");
    }


    //
    private void addClickListener(){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                Intent i = pm.getLaunchIntentForPackage(appitems.get(pos).name.toString());
                DrawerActivity.this.startActivity(i);
            }
        });

    }



}
