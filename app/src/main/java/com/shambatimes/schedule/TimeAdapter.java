package com.shambatimes.schedule;

/**
 * Created by Ben on 09/02/2015.
 */
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class TimeAdapter extends FragmentStatePagerAdapter {
    Context context=null;
    int date = 0;
    int position = 0;

    public TimeAdapter(Context ctxt, FragmentManager mgr, int date) {
        super(mgr);
        this.context=ctxt;
        this.date = date;
    }

    public TimeAdapter(Context ctxt, FragmentManager mgr, int date,int position) {
        super(mgr);
        this.context=ctxt;
        this.date = date;
        this.position = position;
    }


    @Override
    public int getCount() {
        return(48);
    }


    @Override
    public Fragment getItem(int position) {
        return(TimeCardScheduleFragment.newInstance(position, date));
    }

    @Override
    public String getPageTitle(int position) {
        return(TimeCardScheduleFragment.getTitle(context, position));
    }

    public void setDate(int date){
        this.date = date;
    }

}
