package com.example.icbc.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icbc.R;
import com.example.icbc.DataBase.ItemBean;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    // 2个常量标识，因为有两种类型
    public static final int TYPE_TITLE = 1;
    public static final int TYPE_ITEM = 2;

    private List<ItemBean> mData;
    // 构造方法
    public RecyclerViewAdapter(List<ItemBean> data){
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 1. 拿到view
        // 2. 创建holder
        //View view;

        // 根据type类型创建ViewHolder
        if (viewType==TYPE_TITLE){
            //view = View.inflate(parent.getContext(), R.layout.item_title, null);
            return new TitleHolder(View.inflate(parent.getContext(), R.layout.item_title, null));
        }else{
            // RecyclerView显示与定义的Item布局不一致的问题
            // view = View.inflate(parent.getContext(), R.layout.item, null);
            // view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,
             //       false);
            return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,
                    false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        View itemView = holder.itemView;
        ImageView mImage; //itemImage
        TextView mName; // 标题
        TextView mNum;  // 气泡数量

        mName = (TextView) itemView.findViewById(R.id.item_name);
        mName.setText(mData.get(position).getName());
        if (mData.get(position).getType() == 2) {
            mImage = (ImageView) itemView.findViewById(R.id.item);
            mImage.setImageResource(mData.get(position).getImg());
            mNum = (TextView) itemView.findViewById(R.id.bubble_num);
            mNum.setText(mData.get(position).getRedNum());
        }
    }

    @Override
    public int getItemCount() {
        if (mData!=null){
            return mData.size();
        }
        return 0;
    }

    // 复写该方法，根据条件返回条目类型
    @Override
    public int getItemViewType(int position) {
        ItemBean itemBean = mData.get(position);
        if (itemBean.getType()==1){
            return TYPE_TITLE;
        }else  {
            return TYPE_ITEM;
        }
    }

    private class TitleHolder extends RecyclerView.ViewHolder{
        public TitleHolder(View itemView){
            super(itemView);
        }
    }
    private class ItemHolder extends RecyclerView.ViewHolder{
        public ItemHolder(View itemView){
            super(itemView);
        }
    }

}
