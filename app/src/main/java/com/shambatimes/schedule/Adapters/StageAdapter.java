package com.shambatimes.schedule.Adapters;

/**
 * Created by Ben on 09/02/2015.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.shambatimes.schedule.Shambhala;
import com.shambatimes.schedule.StageListScheduleFragment;
import com.shambatimes.schedule.myapplication.R;

public class StageAdapter extends FragmentStatePagerAdapter {
    Context context=null;

    private String TAG = "PagerScheduleFragment";
    private String[] stageTitles;
    private int date;
    private String name;
    private int dividerColor;

    public StageAdapter(Context context, FragmentManager fragmentManager, int date, String name, int dividerColor) {
        super(fragmentManager);
        stageTitles = context.getResources().getStringArray(R.array.stages);
        this.context=context;
        this.date = date;
        this.name = name;
        this.dividerColor = dividerColor;
    }

    @Override
    public int getCount() {
        if(!Shambhala.getFestivalYear(context).equals("2015")) {
            return stageTitles.length;
        }else{
            return stageTitles.length-1;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return(StageListScheduleFragment.newInstance(position, date, name, dividerColor));
    }

    @Override
    public String getPageTitle(int position) {
        return(stageTitles[position]);
    }

    public void setDate(int date){
        this.date = date;
    }

    public void setName(String name) { this.name = name; }

}
