package com.example.icbc.DataBase;

import com.example.icbc.beans.ItemDatas;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class InsertData {
    ItemBeanDao mItemInfoDao = new ItemBeanDao();
    public void insertData() {
        //创建模拟数据
        List<ItemBean> list = new ArrayList<>();
        //item
        String[] names = new String[] {
                "日常办公",
                "邮件", "日程", "云笔记", "投票", "动态口令", "云文档", "AD管理", "业务咨询", "行内公文", "待办", "已办", "阅知",
                "员工服务",
                "请假","出差","HR咨询","发票领用","财务共享","工银大学","招聘报名","在线测评",
                "信息服务",
                "健康打卡", "资讯平台", "待办工作", "掌上数据","考核成绩录入","监控", "云硬盘","暗防动态",
                "专业服务",
                "融安e信", "服务营销", "纪检监察", "技术交流培训", "大数据云服务", "电子签约","课堂培训","人力资源"
        };
        // title
        String[] titles = new String[] {"日常办公","员工服务", "信息服务", "专业服务"};
        List<String> title_List = asList(titles);
        int group=0;
        for(int i=0; i<names.length; i++){
            if(title_List.contains(names[i])){
                switch (names[i]) {
                    case "日常办公":
                        group = 1;
                        break;
                    case "员工服务":
                        group = 2;
                        break;
                    case "信息服务":
                        group = 3;
                        break;
                    case "专业服务":
                        group = 4;
                        break;
                }
            }

            //创建item对象
            ItemBean item = new ItemBean();
            item.setId(i);
            item.setName(names[i]);
            item.setApp_sort(i);
            item.setShow_flag(1);   // 1 ：可见
            // 如果是item, type=1, group=1/2/3/4
            // 如果是title，type=2, group=0
            if(!title_List.contains(item.getName()) ){
                //item, type=2
                item.setType(2);
                item.setGroup_num(group);
                item.setImg(ItemDatas.imgs[i]);
                item.setRedNum("" + 1);
                item.setUrl("www.ww.com");
            }else {
                // title, type=1, group=0
                item.setType(1);
                item.setGroup_num(0);
            }
            list.add(item);
        }
        mItemInfoDao.addData(list);

    }

}
