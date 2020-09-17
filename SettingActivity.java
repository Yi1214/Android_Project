package com.example.icbc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.example.icbc.Adapter.FragmentListAdapter;
import com.example.icbc.Fragment.Setting_Fragment_1;
import com.example.icbc.Fragment.Setting_Fragment_2;
import com.example.icbc.Fragment.Setting_Fragment_3;
import com.example.icbc.Fragment.Setting_Fragment_4;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {
    private static final int RESULT_CODE = 0x11;
    // 创建mfragmentList
    private List<Fragment> mfragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // 监听
        LinearLayout settingBack = (LinearLayout) findViewById(R.id.setting_back);
        settingBack.setOnClickListener(new LinearLayout.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                setResult(RESULT_CODE, intent);
                finish();
            }
        });

        mfragmentList.add(new Setting_Fragment_1());
        mfragmentList.add(new Setting_Fragment_2());
        mfragmentList.add(new Setting_Fragment_3());
        mfragmentList.add(new Setting_Fragment_4());
        initView();
    }

    private void initView(){
        // TabLayout的tab直接从ViewPager的public CharSequence getPageTitle(int position) 方法获取，不需要再addTab()
        TabLayout tab_layout = findViewById(R.id.more_flow_tabs);
        ViewPager viewPager = findViewById(R.id.more_flow_viewPager);
        FragmentListAdapter fragmentAdater = new FragmentListAdapter(getSupportFragmentManager(), mfragmentList);
        viewPager.setAdapter(fragmentAdater);
        tab_layout.setupWithViewPager(viewPager);
    }

}
