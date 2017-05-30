package com.shambatimes.schedule.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.myapplication.R;

public class ListTimeAdapter extends BaseAdapter {

    String[] scheduleTimes;
    LayoutInflater inflater;
    Context context;
    int stageId;
    int selectedPosition;

    public ListTimeAdapter(Context context, String[] scheduleTimes) {
        this.scheduleTimes = scheduleTimes;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return scheduleTimes.length;
    }

    @Override
    public String getItem(int position) {
        return scheduleTimes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item_times, null);
            convertView.setTag(viewHolder);
            viewHolder.time = (TextView) convertView.findViewById(R.id.times);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (scheduleTimes[position].substring(2, 3).equals(":")) {
            viewHolder.time.setText(scheduleTimes[position]);
        } else {
            viewHolder.time.setText("  " + scheduleTimes[position]);
        }

        viewHolder.time.setTextColor(selectedPosition == position ? ColorUtil.themedText(context) : ContextCompat.getColor(context, R.color.secondaryTextColor));
        viewHolder.time.setTypeface(selectedPosition == position ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);

        return convertView;
    }

    private class ViewHolder {
        TextView time;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public void updateListTimes(String[] times) {
        this.scheduleTimes = times;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }
}