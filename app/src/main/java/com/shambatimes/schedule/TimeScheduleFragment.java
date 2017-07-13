package com.shambatimes.schedule;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.MyViewPager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.shambatimes.schedule.Adapters.ListTimeAdapter;
import com.shambatimes.schedule.Adapters.TimeAdapter;
import com.shambatimes.schedule.Settings.SettingsActivity;
import com.shambatimes.Alarms.AlarmHelper;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.Util.EdgeChanger;
import com.shambatimes.schedule.Util.DateUtils;

import de.greenrobot.event.EventBus;

import com.shambatimes.schedule.events.ActionBarColorEvent;
import com.shambatimes.schedule.events.ChangeDateEvent;
import com.shambatimes.schedule.events.DataChangedEvent;
import com.shambatimes.schedule.events.DatabaseLoadFinishedEvent;
import com.shambatimes.schedule.events.SearchSelectedEvent;
import com.shambatimes.schedule.events.ShowHideAlarmSnackbarEvent;
import com.shambatimes.schedule.events.UpdateScheduleByTimeEvent;
import com.shambatimes.schedule.myapplication.R;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

public class TimeScheduleFragment extends Fragment {

    private String TAG = "PagerScheduleFragment";
    private ListView listView;
    MyViewPager pager;

    ListTimeAdapter adapter;
    TimeAdapter timeAdapter;
    View rootView;
    AlarmHelper alarmHelper;

    private int date = 0;
    private int listItemHeight = 0;
    private int listViewHeight = 0;
    private int selectedPosition = 0;

    private boolean listItemClicked = false;

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
        rootView = inflater.inflate(R.layout.schedule_by_time_main_fragment, container, false);
        alarmHelper = new AlarmHelper(getActivity(), rootView);

        if (savedInstanceState != null) {
            selectedPosition = savedInstanceState.getInt("CURRENT_POSITION");
        }

        Bundle args = getArguments();
        int time;

        if (args != null) {
            time = args.getInt("TIME", 0);
            selectedPosition = time;
        } else {
            if (DateUtils.isPrePostFestival(getActivity())) {
                time = 0;
                date = 0;
            } else {
                time = DateUtils.getCurrentTimePosition(getActivity());
                date = DateUtils.getCurrentDay(getActivity());
            }
        }

        setupPager(time);
        setupListView();

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    public void setupPager(int time) {
        pager = (MyViewPager) rootView.findViewById(R.id.pager);
        pager.setAdapter(buildAdapter());
        pager.setCurrentItem(time, false);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                //Event updates the main activities position, so if you leave this fragment and return, you're still at this spot.
                EventBus.getDefault().postSticky(new UpdateScheduleByTimeEvent(i));
                updateListSelectionIndicator(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }

            private void updateListSelectionIndicator(int i) {
                selectedPosition = i;
                adapter.setSelectedPosition(selectedPosition);

                if (listViewHeight != 0) {
                    if (!listItemClicked) {
                        listView.smoothScrollToPositionFromTop(selectedPosition, listViewHeight / 2 - listItemHeight / 2);
                    } else {
                        listItemClicked = false;
                    }
                }
            }
        });
    }

    public void setupListView() {
        listView = (ListView) rootView.findViewById(R.id.listViewTimes);

        adapter = new ListTimeAdapter(getActivity(), generateListTimes());
        adapter.setSelectedPosition(selectedPosition);

        listView.setAdapter(adapter);
        listView.getLayoutParams().width = (int) (getWidestView(getActivity(), adapter) * 1.05);

        listView.setVerticalScrollBarEnabled(false);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Not the most efficient option to select/deselect, but list is tiny so resource use isn't relevant
                selectedPosition = position;
                adapter.setSelectedPosition(selectedPosition);
                listItemClicked = true;
                EventBus.getDefault().post(new UpdateScheduleByTimeEvent(position));
            }
        });

        //Post a runnable so the height will be available after it's drawn.  Use that to determine
        //where the selection indicator should positioned in the list view
        listView.post(new Runnable() {
            @Override
            public void run() {
                listViewHeight = listView.getHeight();
                if (listView.getCount() > 0 && listItemHeight == 0) {
                    View view = listView.getChildAt(0);
                    if (view != null) {
                        listItemHeight = view.getHeight();
                    }
                }
                listView.setSelectionFromTop(selectedPosition, listViewHeight / 2 - listItemHeight / 2);
            }
        });
    }

    public void setPagerToNow() {
        if (pager != null) {
            int time = DateUtils.getCurrentTimePosition(getActivity());
            pager.setCurrentItem(time, false);
        }
    }

    private String[] generateListTimes() {

        String[] times = new String[48];


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String format = preferences.getString(SettingsActivity.TIME_FORMAT, "24");
        DateTimeFormatter dateStringFormat = DateUtils.getTimeFormatTwo(format);

        for (int x = 0; x < 48; x++) {
            DateTime dateTime = DateTime.now().withZone(Constants.timeZone).withTimeAtStartOfDay().plusMinutes(Constants.REFERENCE_TIME);
            dateTime = dateTime.plusMinutes(30 * x);

            if (format.equals("24")) {
                times[x] = " " + dateStringFormat.print(dateTime) + " ";
            } else {
                times[x] = dateStringFormat.print(dateTime);
            }

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
    public static int getWidestView(Context context, ListTimeAdapter adapter) {
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
        ColorUtil.setCurrentThemeColor(event.getColor());
        EdgeChanger.setEdgeGlowColor(pager, ColorUtil.dividerColor(getActivity()));
        EdgeChanger.setEdgeGlowColor(listView, ColorUtil.dividerColor(getActivity()));

        if (adapter != null) {
            adapter.setStageId(event.getStage());
        }
    }

    public void onEventMainThread(UpdateScheduleByTimeEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (pager.getCurrentItem() != event.getPosition()) {
            pager.setCurrentItem(event.getPosition(), false);
        }
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
        if (Shambhala.getFestivalYear(getActivity()).equals("2015")) {
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
        }

        date = event.getPosition();

        resetListViewValues();
    }

    public void onEventMainThread(final ShowHideAlarmSnackbarEvent event) {

        if (event.getArtist() != null) {

            alarmHelper.setOnAlarmStateChangedListener(new AlarmHelper.OnAlarmStateChangedListener() {
                @Override
                public void alarmStateChanged() {
                    if (event.getImageView() != null) {
                        MainActivity.shambhala.updateArtistById(event.getArtist().getId());
                        event.getImageView().setAlpha(0f);
                        event.getImageView().setVisibility(View.VISIBLE);
                        event.getImageView().animate().setDuration(500).alpha(1f);

                        //Delaying event as it interrupts animations
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                EventBus.getDefault().postSticky(new DataChangedEvent(true, event.getArtist().getId()));
                            }
                        }, 500);

                    }
                }
            });

            alarmHelper.showSetAlarmSnackBar(event.getArtist());
        } else {
            alarmHelper.dismissSnackbar();
        }
    }

    private void resetListViewValues() {
        adapter.updateListTimes(generateListTimes());
        adapter.notifyDataSetChanged();

        if (timeAdapter != null) {
            timeAdapter.setDate(date);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("CURRENT_POSITION", selectedPosition);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}