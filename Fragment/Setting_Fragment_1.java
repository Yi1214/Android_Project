package com.example.icbc.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.icbc.Adapter.GridViewAdapter;
import com.example.icbc.DataBase.ItemBeanDao;
import com.example.icbc.R;
import com.example.icbc.beans.FragItemBean;
import com.example.icbc.beans.FragItemDatas;
import com.example.icbc.DataBase.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class Setting_Fragment_1 extends Fragment {
    // 创建模拟数据mData
    List<ItemBean> mData = new ArrayList<>();
    ItemBeanDao mItemInfoDao = new ItemBeanDao();
    private View view;    // 缓存Fragment view
    private GridView gridView;
    private GridViewAdapter gridViewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mData.size()==0) {
            initData();
        }
        // View view = inflater.inflate(R.layout.setting_fragment_1, container, false);
        // GridView gridView = (GridView) view.findViewById(R.id.gridView);
        // 2.为数据源设置适配器
        // GridViewAdapter gridViewAdapter = new GridViewAdapter(mData, getActivity());
        // 3.将适配过后点数据显示在GridView 上
        // gridView.setAdapter(gridViewAdapter);
        if(view==null) {
            view = inflater.inflate(R.layout.setting_fragment_1, container, false);
            gridView = (GridView) view.findViewById(R.id.gridView);
            gridViewAdapter = new GridViewAdapter(mData, getActivity());
            gridView.setAdapter(gridViewAdapter);
        }else {
            // 缓存的view需要判断是否已经被加过parent，
            // 如果有parent需要从parent删除，
            // 要不然会发生这个view已经有parent的错误。
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null){
                parent.removeView(view);
            }
        }
        return view;
    }

    // 从数据库取数据
    private void initData() {
        /**
        List<ItemBean> list = mItemInfoDao.findAllData(ItemBean.class);

        for (ItemBean item:list) {
            if(item.getGroup_num()!=1) {
                continue;
            } else {
                mData.add(item);
            }
        }**/
        mData = mItemInfoDao.findDataByGroup(ItemBean.class, 1);
    }

    //初始化数据,数据库无数据时
    private void initNoData(){
        //创建模拟数据
        //Frag_item
        String[] names = new String[] {
                "工银大学", "业务咨询", "工银e差旅", "邮件审批测试",
                "代办", "动态口令", "行内公文", "新网络大学",
                "云笔记", "重庆督办", "工银e视讯", "邮件",
                "北中心", "董事会办公", "云文档", "测试应用",
                "创新研发中心" , "已办", "分行特色", "日程"
        };
        for(int i=0; i<names.length; i++) {
            /**FragItemBean item = new FragItemBean();
            item.setId("item_" + i);
            item.setImg(FragItemDatas.imgs[i]);
            item.setName(names[i]);
            item.setIsExist(1);   // 1 存在，-号
            mData.add(item);**/
            ItemBean item = new ItemBean();
            item.setId(i);
            item.setImg(FragItemDatas.imgs[i]);
            item.setName(names[i]);
            item.setShow_flag(1);   // 1 存在，-号
            mData.add(item);
        }
    }
}
