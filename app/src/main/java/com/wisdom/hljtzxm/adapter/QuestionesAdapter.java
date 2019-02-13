package com.wisdom.hljtzxm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wisdom.hljtzxm.R;
import com.wisdom.hljtzxm.model.QuestionModel;

import java.util.List;

/**
 * @author HanXueFeng
 * @ProjectName project£º Hljtzxm
 * @class package£ºcom.wisdom.hljtzxm.adapter
 * @class describe£º
 * @time 2019/2/13 16:32
 * @change
 */
public class QuestionesAdapter extends BaseAdapter {
    private List<QuestionModel> listData;
    private Context context;

    public QuestionesAdapter(List<QuestionModel> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_questiones, null, false);
            holder = new MyHolder();
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_content = convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }
        holder.tv_title.setText(listData.get(position).getQuestion_description());
        holder.tv_content.setText(listData.get(position).getQuestion_answer());
        return convertView;
    }


    class MyHolder {
        TextView tv_title;
        TextView tv_content;
    }
}
