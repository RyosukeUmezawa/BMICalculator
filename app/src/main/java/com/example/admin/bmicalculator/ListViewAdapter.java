package com.example.admin.bmicalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 2015/07/03.
 */
public class ListViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<HistoryActivity.History> dataList;

    public ListViewAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setListView(ArrayList<HistoryActivity.History> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dataList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.activity_history, parent, false);

        ((TextView) convertView.findViewById(R.id.height)).setText(String.valueOf(dataList.get(position).getHeight()));
        ((TextView) convertView.findViewById(R.id.weight)).setText(String.valueOf(dataList.get(position).getWeight()));
        ((TextView) convertView.findViewById(R.id.bmi)).setText(String.valueOf(dataList.get(position).getBMI()));
        ((TextView) convertView.findViewById(R.id.date)).setText(dataList.get(position).getDate());

        return convertView;
    }


}
