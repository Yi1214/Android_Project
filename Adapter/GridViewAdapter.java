package com.example.icbc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icbc.DataBase.ItemBeanDao;
import com.example.icbc.R;
import com.example.icbc.beans.FragItemBean;
import com.example.icbc.beans.FragItemDatas;
import com.example.icbc.DataBase.ItemBean;

import java.util.List;


public class GridViewAdapter extends BaseAdapter {
    private List<ItemBean> mData;
    private Context mContext;
    //构造方法传数据
    public GridViewAdapter(List<ItemBean> data, Context mContext){
        this.mData = data;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if (mData!=null){
            return mData.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ItemBean data = mData.get(position);
        final ViewHolder holder;
        if (convertView==null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.frag_item, parent, false);
            holder = new ViewHolder();
            // 第一次加载创建View，其余复用 View
            holder.imageView = (ImageView) convertView.findViewById(R.id.frag_item_img);
            holder.deleteImg = (ImageView) convertView.findViewById(R.id.delete);
            holder.textView = (TextView) convertView.findViewById(R.id.frag_item_name);
            convertView.setTag(holder);   //将Holder存储到convertView中
        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(mData.get(position).getName());
        holder.imageView.setImageResource(mData.get(position).getImg());

        // 根据值show_flag设置不同数据内容
        if(data.getShow_flag()==1) {  // 1显示，0不显示
            holder.deleteImg.setImageResource(R.drawable.delete);
        }else {
            holder.deleteImg.setImageResource(R.drawable.insert);
        }

        holder.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemBeanDao itemBeanDao = new ItemBeanDao();
                List<ItemBean> beans = itemBeanDao.findDataById(ItemBean.class, data.getId());
                ItemBean bean = beans.get(0);
                int show_flag = bean.getShow_flag();
                if(show_flag==1) {
                    bean.setShow_flag(0);
                    holder.deleteImg.setImageResource(R.drawable.insert);
                }else{
                    bean.setShow_flag(1);
                    holder.deleteImg.setImageResource(R.drawable.delete);
                }
                itemBeanDao.updateData(bean, "show_flag");
            }
        });

        return convertView;
    }
    static class ViewHolder {
        ImageView imageView;
        ImageView deleteImg;
        TextView textView;
    }
}
