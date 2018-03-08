package com.kuncham.kalljobs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by abc on 3/8/2018.
 */

class MyAdapter extends BaseAdapter{
    Context ct;
    List<Jobs> job;

    public MyAdapter(Context ct, List<Jobs> job, int list) {
        this.ct = ct;
        this.job = job;
    }

    @Override
    public int getCount() {
        return job.size();
    }

    @Override
    public Object getItem(int position) {
        return job.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View v = li.inflate(R.layout.list, null);

        TextView tv1 = (TextView) v.findViewById(R.id.tv_job);
        Jobs p=job.get(position);
        tv1.setText(p.getJobName());

        return v;
    }
}
