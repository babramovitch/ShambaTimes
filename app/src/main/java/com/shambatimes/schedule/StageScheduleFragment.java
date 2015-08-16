package com.shambatimes.schedule;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import de.greenrobot.event.EventBus;
import com.shambatimes.schedule.events.ActionBarColorEvent;
import com.shambatimes.schedule.events.ChangeDateEvent;
import com.shambatimes.schedule.myapplication.R;


public class StageScheduleFragment extends Fragment {

    private String TAG = "TabbedScheduleFragment";

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private int currentColor = 0xFF666666;

    int[] stageColors;
    int stageSetTo;

    String nameSetTo;

    private StageAdapter stageAdapter;
    private int date = 0;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.schedule_by_stage_fragment, container, false);

        Bundle args = getArguments();

        stageSetTo = 0;
        nameSetTo = "";

        if (args != null) {
            stageSetTo = args.getInt("STAGE", 0);
            nameSetTo = args.getString("NAME", "");
        }

        stageColors = this.getResources().getIntArray(R.array.stage_colors);

        pager = (ViewPager) result.findViewById(R.id.tabbed_pager);
        pager.setAdapter(buildAdapter());

        pager.setCurrentItem(stageSetTo, false);

        tabs = (PagerSlidingTabStrip) result.findViewById(R.id.tabs);

        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                changeColor(stageColors[i]);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        tabs.setViewPager(pager);

        changeColor(stageColors[stageSetTo]);

        return (result);
    }

    private void changeColor(int newColor) {


        tabs.setIndicatorColor(newColor);
        currentColor = newColor;
        EventBus.getDefault().postSticky(new ActionBarColorEvent(currentColor));

    }

    private PagerAdapter buildAdapter() {
        stageAdapter = new StageAdapter(getActivity(), getChildFragmentManager(), date, nameSetTo, stageColors[stageSetTo]);
        return (stageAdapter);
    }

    public void onEventMainThread(ChangeDateEvent event) {
        date = event.getPosition();
        stageAdapter.setDate(date);
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}