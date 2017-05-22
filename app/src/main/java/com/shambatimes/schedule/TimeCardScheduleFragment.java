package com.shambatimes.schedule;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shambatimes.schedule.Settings.SettingsActivity;
import com.shambatimes.schedule.Util.AlarmHelper;
import com.shambatimes.schedule.Util.AnimationHelper;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.Util.DateUtils;
import com.shambatimes.schedule.animations.MyTransitionDrawable;
import com.shambatimes.schedule.events.ActionBarColorEvent;
import com.shambatimes.schedule.events.ChangeDateEvent;
import com.shambatimes.schedule.events.DataChangedEvent;
import com.shambatimes.schedule.events.ChangeTimePagersTimeColorEvent;
import com.shambatimes.schedule.events.ShowHideAlarmSnackbarEvent;
import com.shambatimes.schedule.events.ToggleToGridEvent;
import com.shambatimes.schedule.events.ToggleToStageEvent;
import com.shambatimes.schedule.events.ToggleToTimeEvent;
import com.shambatimes.schedule.myapplication.R;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import de.greenrobot.event.EventBus;

import static com.shambatimes.schedule.Constants.ANIMATION_DURATION_HEARTS;

public class TimeCardScheduleFragment extends Fragment {
    private static final String TIME_POSITION = "position";
    private static final String DATE_POSITION = "date";

    private int timePosition;

    private View rootView;

    private int date = 0;
    private int stage;
    private BaseAdapter scheduleAdapter;
    private GridView gridView;

    AlarmHelper alarmHelper;
    TextView editor;
    DateTimeFormatter dateStringFormat;

    static TimeCardScheduleFragment newInstance(int position, int currentDate) {
        TimeCardScheduleFragment frag = new TimeCardScheduleFragment();
        Bundle args = new Bundle();

        args.putInt(TIME_POSITION, position);
        args.putInt(DATE_POSITION, currentDate);

        frag.setArguments(args);

        return (frag);
    }

    static String getTitle(Context ctxt, int position) {
        return ("");
    }

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

        rootView = inflater.inflate(R.layout.schedule_by_time_grid, container, false);
        alarmHelper = new AlarmHelper(getActivity(), rootView);

        timePosition = getArguments().getInt(TIME_POSITION, 0);
        date = getArguments().getInt(DATE_POSITION, 0);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        dateStringFormat = DateUtils.getTimeFormat(preferences.getString(SettingsActivity.TIME_FORMAT, "24"));

        setHeaderTimes();
        setupGridView();

        return (rootView);
    }

    private void setHeaderTimes() {

        editor = (TextView) rootView.findViewById(R.id.headline_time);

        DateTime startTime = DateTime.now().withZone(Constants.timeZone).withTimeAtStartOfDay().plusMinutes(Constants.REFERENCE_TIME);
        DateTime endTime;

        startTime = startTime.plusMinutes(30 * timePosition);
        endTime = startTime.plusMinutes(30);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        DateTimeFormatter dateStringFormat = DateUtils.getTimeFormatTwo(preferences.getString(SettingsActivity.TIME_FORMAT, "24"));

        editor.setText(dateStringFormat.print(startTime) + " to " + dateStringFormat.print(endTime));

        editor.setVisibility(View.GONE);
    }

    public void onEventMainThread(ChangeTimePagersTimeColorEvent event) {
        final TextView editor = (TextView) rootView.findViewById(R.id.headline_time);
        int[] stageColors = ColorUtil.getStageColors();
        if (editor != null && timePosition == DateUtils.getCurrentTimePosition(getActivity())) {
            editor.setTextColor(ContextCompat.getColor(getActivity(), stageColors[stage]));
            editor.postDelayed(new Runnable() {
                @Override
                public void run() {
                    editor.setTextColor(Color.BLACK);
                }
            }, 300);
        }
    }

    private void setupGridView() {
        gridView = (GridView) rootView.findViewById(R.id.gridView);

        scheduleAdapter = new ScheduleAdapter(getActivity());
        gridView.setAdapter(scheduleAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout childView = (LinearLayout) parent.getChildAt(position);
                CardView cardView = (CardView) childView.getChildAt(0);
                RelativeLayout relativeLayout = (RelativeLayout) cardView.getChildAt(0);
                TextView artistNameTextView = (TextView) relativeLayout.getChildAt(0);

                EventBus.getDefault().post(new ToggleToGridEvent(timePosition));

                return false;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinearLayout childView = (LinearLayout) parent.getChildAt(position);
                CardView cardView = (CardView) childView.getChildAt(0);
                RelativeLayout relativeLayout = (RelativeLayout) cardView.getChildAt(0);
                TextView artistNameTextView = (TextView) relativeLayout.getChildAt(0);

                EventBus.getDefault().post(new ToggleToStageEvent(position, artistNameTextView.getText().toString()));
            }
        });
    }

    public class ScheduleAdapter extends BaseAdapter {
        private Context mContext;
        private String[] stageNames = getActivity().getResources().getStringArray(R.array.stages);

        int[] stageColors = ColorUtil.getStageColors();

        public ScheduleAdapter(Context context) {
            mContext = context;

        }

        public int getCount() {
            return 7;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View gridView;

            gridView = inflater.inflate(R.layout.schedule_by_time_card, null);

            CardView card = (CardView) gridView.findViewById(R.id.time_card);
            TextView stageName = (TextView) gridView.findViewById(R.id.stage_text);
            TextView artistName = (TextView) gridView.findViewById(R.id.artist_text);
            TextView artistTime = (TextView) gridView.findViewById(R.id.time_text);

            stageName.setText(stageNames[position]);

            if (!ColorUtil.nightMode) {
                card.setCardBackgroundColor(ContextCompat.getColor(getActivity(), stageColors[position]));
            } else {
                card.setCardBackgroundColor(ContextCompat.getColor(getActivity(), R.color.cardBackgroundColor));
            }

            final ImageView image = (ImageView) gridView.findViewById(R.id.card_favorited);
            final Artist artist = MainActivity.shambhala.getArtistsByDayAndPositionAndStage(date, timePosition, position);

            if (Shambhala.getFestivalYear(getActivity()).equals("2015") && position == 6) {
                card.setVisibility(View.GONE);
            } else {
                card.setVisibility(View.VISIBLE);
            }

            if (artist != null) {
                artistName.setText(artist.getAristName());

                artistTime.setText(DateUtils.formatTime(dateStringFormat, artist.getStartTimeString()) +
                        " - "
                        + DateUtils.formatTime(dateStringFormat, artist.getEndTimeString()));

                image.setImageDrawable(AnimationHelper.getFavoriteTransitionDrawable(getActivity(), artist.isFavorite()));
                if (ColorUtil.nightMode) {
                    image.setColorFilter(ContextCompat.getColor(getActivity(), ColorUtil.getStageColors()[artist.getStage()]));
                }
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (artist != null) {
                            if (artist.isFavorite()) {
                                MyTransitionDrawable transitionDrawable = (MyTransitionDrawable) image.getDrawable();
                                transitionDrawable.favoriteReverse(ANIMATION_DURATION_HEARTS);
                                artist.setFavorite(false);
                                artist.setIsAlarmSet(false);
                                artist.save();

                                alarmHelper.cancelAlarm(artist);
                                EventBus.getDefault().post(new ShowHideAlarmSnackbarEvent(null));

                            } else {
                                MyTransitionDrawable transitionDrawable = (MyTransitionDrawable) image.getDrawable();
                                transitionDrawable.favoriteStart(ANIMATION_DURATION_HEARTS);
                                artist.setFavorite(true);
                                artist.save();

                                EventBus.getDefault().post(new ShowHideAlarmSnackbarEvent(artist));

                            }
                        }
                        //TODO is this event needed? It's stopping the animations.  It may cause the artist to NOT be updated elsewhere?
                        //EventBus.getDefault().postSticky(new DataChangedEvent(true, artist.getId()));
                    }
                });
            } else {
                //artistName.setText("Stage Closed");
                //card.setCardBackgroundColor(ContextCompat.getColor(getActivity(), ColorUtil.getLightStageColors()[position]))
                //   image.setImageResource(R.drawable.new_favourite_border);
            }
            return gridView;
        }
    }

    public void onEventMainThread(ChangeDateEvent event) {
        if (event.getPosition() != date) {
            if (Shambhala.getFestivalYear(getActivity()).equals("2015")) {
                if (date == 3 && event.getPosition() != 3) {
                    timePosition = timePosition + 2;
                } else if (date != 3 && event.getPosition() == 3) {
                    timePosition = timePosition - 2;
                }
            }

            if (timePosition < 0) {
                timePosition = 0;
            }

            setHeaderTimes();
            date = event.getPosition();
            scheduleAdapter.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(DataChangedEvent event) {
        EventBus.getDefault().removeStickyEvent(event);

        if (event.isChanged()) {
            MainActivity.shambhala.updateArtistById(event.getArtistId());
            scheduleAdapter = new ScheduleAdapter(getActivity());
            gridView.setAdapter(scheduleAdapter);
        }
    }

    public void onEventMainThread(ActionBarColorEvent event) {
        stage = event.getStage();
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