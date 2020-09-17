package com.example.icbc.Adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;


public class FragmentListAdapter extends FragmentPagerAdapter {
    // 4个fragment标题
    //static final int NUM_ITEMS = 4;
    private String[] mtitles = new String[]{"日常办公","员工服务","信息服务","专业服务"};
    private List<Fragment> mfragmentList;

    public FragmentListAdapter(FragmentManager fm, List<Fragment> mfragmentList) {
        super(fm);
        this.mfragmentList = mfragmentList;
    }

    @Override
    public int getCount() {
        return mtitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mtitles[position];
    }
}