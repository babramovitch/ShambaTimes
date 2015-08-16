package com.shambatimes.schedule;

/**
 * Created by Ben on 09/02/2015.
 */



import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class StageAdapter extends FragmentStatePagerAdapter {
    Context context=null;

    private String TAG = "PagerScheduleFragment";
    private final String[] TITLES = {"Pagoda", "Fractal Forest", "Grove", "Living Room", "Village", "Amphitheatre"};
    private int date;
    private String name;
    private int dividerColor;

    public StageAdapter(Context ctxt, FragmentManager mgr, int date, String name, int dividerColor) {
        super(mgr);
        this.context=ctxt;
        this.date = date;
        this.name = name;
        this.dividerColor = dividerColor;
    }


    @Override
    public int getCount() {
        return TITLES.length;
    }


    @Override
    public Fragment getItem(int position) {
        return(StageListScheduleFragment.newInstance(position, date, name, dividerColor));
    }


    @Override
    public String getPageTitle(int position) {
        return(TITLES[position]);
    }

    public void setDate(int date){
        this.date = date;
    }

}
