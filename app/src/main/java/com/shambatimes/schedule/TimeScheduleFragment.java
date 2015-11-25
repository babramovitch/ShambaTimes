package com.shambatimes.schedule;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MyViewPager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.shambatimes.schedule.Util.AlarmHelper;
import com.shambatimes.schedule.Util.EdgeChanger;
import com.shambatimes.schedule.Util.DateUtils;

import de.greenrobot.event.EventBus;

import com.shambatimes.schedule.events.ActionBarColorEvent;
import com.shambatimes.schedule.events.ChangeDateEvent;
import com.shambatimes.schedule.events.DatabaseLoadFinishedEvent;
import com.shambatimes.schedule.events.SearchSelectedEvent;
import com.shambatimes.schedule.events.ShowHideAlarmSnackbarEvent;
import com.shambatimes.schedule.events.UpdateScheduleByTimeEvent;
import com.shambatimes.schedule.myapplication.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class TimeScheduleFragment extends Fragment {

    private String TAG = "PagerScheduleFragment";
    private ListView listView;
    MyViewPager pager;

    TimeAdapter timeAdapter;
    View result;
    AlarmHelper alarmHelper;

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
        result = inflater.inflate(R.layout.schedule_by_time_main_fragment, container, false);
        alarmHelper = new AlarmHelper(getActivity(),result);
        Bundle args = getArguments();
        int time;

        if (args != null) {
            time = args.getInt("TIME", 0);
        } else {

            if (DateUtils.isPrePostFestival()) {
                time = 0;
                date = 0;
            } else {
                time = DateUtils.getCurrentTimePosition();
                date = DateUtils.getCurrentDay();
            }
        }

        pager = (MyViewPager) result.findViewById(R.id.pager);
        pager.setAdapter(buildAdapter());
        pager.setCurrentItem(time, false);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                EventBus.getDefault().postSticky(new UpdateScheduleByTimeEvent(i));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        listView = (ListView) result.findViewById(R.id.listViewTimes);
        ;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item, android.R.id.text1, generateListTimes());

        listView.setAdapter(adapter);

        listView.getLayoutParams().width = (int) (getWidestView(getActivity(), adapter) * 1.05);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                EventBus.getDefault().post(new UpdateScheduleByTimeEvent(position));

            }
        });



        return (result);
    }

    private String[] generateListTimes() {

        String[] times = new String[48];
        String time = "01/01/2001 10:00:00";

        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("HH:mm aa");

        for (int x = 0; x < 48; x++) {
            DateTime dateTime = DateTime.now().withZone(Constants.timeZone).withTimeAtStartOfDay().plusMinutes(Constants.REFERENCE_TIME);
            dateTime = dateTime.plusMinutes(30 * x);
            times[x] = dateStringFormat.print(dateTime);
        }

        return times;
    }

    /**
     * I'm using this so I can get the widest possible room for the stage artist title
     * <p/>
     * Computes the widest view in an genreAdapter, best used when you need to wrap_content on a ListView, please be careful
     * and don't use it on an genreAdapter that is extremely numerous in items or it will take a long time.
     *
     * @param context Some context
     * @param adapter The genreAdapter to process
     * @return The pixel width of the widest View
     * <p/>
     * http://stackoverflow.com/a/13959716/2408033
     */
    public static int getWidestView(Context context, ArrayAdapter<String> adapter) {
        int maxWidth = 0;
        View view = null;
        FrameLayout fakeParent = new FrameLayout(context);
        for (int i = 0, count = adapter.getCount(); i < count; i++) {
            view = adapter.getView(i, view, fakeParent);
            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            int width = view.getMeasuredWidth();
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }


    private PagerAdapter buildAdapter() {
        timeAdapter = new TimeAdapter(getActivity(), getChildFragmentManager(), date);
        return timeAdapter;
    }

    public void onEventMainThread(DatabaseLoadFinishedEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (pager != null) {
            int currentPage = pager.getCurrentItem();
            pager.setAdapter(buildAdapter());
            pager.setCurrentItem(currentPage);
        }
    }

    public void onEventMainThread(ActionBarColorEvent event) {
        EdgeChanger.setEdgeGlowColor(pager, event.getColor());
        EdgeChanger.setEdgeGlowColor(listView, event.getColor());
    }

    public void onEventMainThread(UpdateScheduleByTimeEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (pager.getCurrentItem() != event.getPosition())
            pager.setCurrentItem(event.getPosition(), false);
    }

    public void onEventMainThread(SearchSelectedEvent event) {
        EventBus.getDefault().removeStickyEvent(SearchSelectedEvent.class);
        Artist artist = event.getArtist();
        if (pager.getCurrentItem() != artist.getStartPosition() || date != artist.getDay()) {
            date = artist.getDay();
            pager.setAdapter(buildAdapter());
            pager.setCurrentItem(artist.getStartPosition(), false);
        }

        date = artist.getDay();

        resetListViewValues();

    }

    public void onEventMainThread(ChangeDateEvent event) {

        //The genreAdapter needs to be reset when we change to/from Sunday due to the page counts differing
        //What happens if I just try to change the content is the first page looks good, but then
        //as you page, the content becomes wrong until it properly refreshes.
        if (date == 3 && event.getPosition() != 3) {
            date = event.getPosition();
            int currentPage = pager.getCurrentItem();
            pager.setAdapter(buildAdapter());
            pager.setCurrentItem(currentPage + 2);
        } else if (date != 3 && event.getPosition() == 3) {
            date = event.getPosition();
            int currentPage = pager.getCurrentItem();
            pager.setAdapter(buildAdapter());
            pager.setCurrentItem(currentPage - 2);
        }

        date = event.getPosition();

        resetListViewValues();
    }

    public void onEventMainThread(ShowHideAlarmSnackbarEvent event){

        if(event.getArtist() != null) {
            alarmHelper.showSetAlarmSnackBar(event.getArtist());
        }else{
            alarmHelper.dismissSnackbar();
        }
    }



    private void resetListViewValues(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item, android.R.id.text1, generateListTimes());

        listView.setAdapter(adapter);

        if (timeAdapter != null) {
            timeAdapter.setDate(date);
        }
    }

    public void dataLoaded() {
        timeAdapter.notifyDataSetChanged();
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