package com.example.icbc;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.icbc.Adapter.RecyclerViewAdapter;
import com.example.icbc.DataBase.InsertData;
import com.example.icbc.DataBase.ItemBean;
import com.example.icbc.DataBase.ItemBeanDao;
import com.example.icbc.beans.ItemDatas;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import static java.util.Arrays.*;
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 0x11;
    private static final int RESULT_CODE = 0x11;
    private ItemBeanDao mItemInfoDao;
    private RecyclerView mRecyclerView;  // ctrl+alt+f抽取成员变量
    private List<ItemBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        x.view().inject(this);
        mItemInfoDao = new ItemBeanDao();
        /**
         * 插入数据
         InsertData insertData = new InsertData();
         insertData.insertData();
         ***/

        // 右上角的设置图标，点击监听
        ImageView settingButton = (ImageView) findViewById(R.id.setting);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE && resultCode==RESULT_CODE){
            show();
        }
    }

    private void show() {
        mData.clear();
        initData();
        // 找到控件
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_view);
        // 准备数据，目前只是模拟数据，现实中从网络获取

        //创建和设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mData.get(position).getType()==1){
                    return 4;
                }else {
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        // 创建适配器
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mData);
        // 设置适配器
        mRecyclerView.setAdapter(recyclerViewAdapter);
    }

    // 数据库有数据时模拟数据
    private void initData() {
        /**
        List<ItemBean> list = mItemInfoDao.findAllData(ItemBean.class);
        for(ItemBean itemBean: list) {
            if(itemBean.getShow_flag()==1) {
                mData.add(itemBean);
            }else{
                continue;
            }
        }
         **/
        mData = mItemInfoDao.findDataByShowFlag(ItemBean.class,1);
    }

    // 这个方法用于无数据时模拟数据
    // List<ItemBean> --> Adapter --> setAdapter --> 显示数据
    private void initNoData() {
        //创建模拟数据
        //item
        String[] names = new String[] {
                "日常办公",
                "邮件", "日程", "云笔记", "投票", "动态口令", "云文档", "AD管理", "业务咨询", "行内公文", "待办", "已办", "阅知",
                "员工服务",
                "请假","出差","HR咨询","发票领用","财务共享","工银大学","招聘报名","在线测评"
        };
        // title
        String[] titles = new String[] {"日常办公","员工服务"};
        List<String> title_List = asList(titles);

        for(int i=0; i<names.length; i++){
            //创建item对象
            ItemBean item = new ItemBean();
            item.setName(names[i]);
            item.setApp_sort(i);
            if(!title_List.contains(item.getName()) ){
                //item, type=2
                item.setType(2);
                item.setGroup_num(2);
                item.setImg(ItemDatas.imgs[i]);
                item.setUrl("www.ww.com");
                if(i<=21) {
                    item.setRedNum("" + 1);
                }else {
                    item.setRedNum("" + 0);
                }
            }else {
                // title, type=1
                item.setType(1);
                item.setGroup_num(1);
            }
            item.setId(i);
            mData.add(item);
        }
    }
}
