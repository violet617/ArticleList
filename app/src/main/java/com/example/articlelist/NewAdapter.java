package com.example.articlelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class NewAdapter extends BaseAdapter {

    private ArrayList<NewsInfo> mData;
    private Context mContext;

    public NewAdapter(ArrayList<NewsInfo> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
            holder=new ViewHolder();
            holder.tv_img=convertView.findViewById(R.id.tv_img);
            holder.tv_title =  convertView.findViewById(R.id.tv_title);
            holder.tv_shareuser =convertView.findViewById(R.id.tv_author);
            holder.tv_chatername = convertView.findViewById(R.id.tv_type);
            holder.tv_nicedate =  convertView.findViewById(R.id.tv_time);
            holder.tv_link=convertView.findViewById(R.id.tv_link);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder) convertView.getTag();
        }

        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_chatername.setText(mData.get(position).getChapterName());
        holder.tv_shareuser.setText(mData.get(position).getShareUser());
        holder.tv_nicedate.setText(mData.get(position).getNiceDate());
        holder.tv_link.setText(mData.get(position).getLink());
        if(mData.get(position).isCollcet()){
            holder.tv_img.setImageResource(R.drawable.button_selector);
        }else {
            holder.tv_img.setImageResource(R.drawable.button_selector1);
        }
        holder.tv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.get(position).setCollcet(!mData.get(position).isCollcet());
                if(mData.get(position).isCollcet()){
                    holder.tv_img.setImageResource(R.drawable.button_selector);
                }else {
                    holder.tv_img.setImageResource(R.drawable.button_selector1);
                }
            }
        });

        return convertView;

    };
    class ViewHolder{
        public TextView tv_title;
        public TextView tv_shareuser;
        public TextView tv_chatername;
        public TextView tv_nicedate;
        public TextView tv_link;
        public ImageView tv_img;
    }
}