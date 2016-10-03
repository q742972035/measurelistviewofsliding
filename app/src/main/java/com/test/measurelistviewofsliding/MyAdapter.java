package com.test.measurelistviewofsliding;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 张忆 on 2016/9/30.
 */

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private List<Integer> mList;

    public MyAdapter(Context context,List<Integer> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Integer getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView==null){
            convertView = View.inflate(mContext,R.layout.lv_item,null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.tv.setText(String.valueOf(getItem(position)*100));

        return convertView;
    }

    static class ViewHolder{
        TextView tv;
        public ViewHolder(View view){
            tv= (TextView) view.findViewById(R.id.tv);
        }
    }
}
