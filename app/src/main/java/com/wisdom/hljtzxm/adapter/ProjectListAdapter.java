package com.wisdom.hljtzxm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wisdom.hljtzxm.R;
import com.wisdom.hljtzxm.model.ProjectListModel;

import java.util.List;

/**
 * @author HanXueFeng
 * @ProjectName project： Hljtzxm
 * @class package：com.wisdom.hljtzxm.adapter
 * @class describe：
 * @time 2019/1/31 14:08
 * @change
 */
public class ProjectListAdapter extends BaseAdapter {
    private Context context;
    private List<ProjectListModel> listData;

    public ProjectListAdapter(Context context, List<ProjectListModel> listData) {
        this.context = context;
        this.listData = listData;
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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_project_list, null, false);
            holder.textView = convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(listData.get(position).getCatalog_name());

        return convertView;
    }


    class ViewHolder {
        TextView textView;
    }
}
