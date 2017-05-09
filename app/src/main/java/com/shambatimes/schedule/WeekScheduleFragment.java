package com.shambatimes.schedule;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shambatimes.schedule.Util.AlarmHelper;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.Util.DateUtils;
import com.shambatimes.schedule.events.ChangeDateEvent;
import com.shambatimes.schedule.events.SearchSelectedEvent;
import com.shambatimes.schedule.myapplication.R;
import com.shambatimes.weekview.DateTimeInterpreter;
import com.shambatimes.weekview.MonthLoader;
import com.shambatimes.weekview.WeekView;
import com.shambatimes.weekview.WeekViewEvent;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.greenrobot.event.EventBus;


public class WeekScheduleFragment extends Fragment implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener {
    private static final String TAG = "WeekSchedule";

    private View rootView;
    private WeekView mWeekView;
    private boolean favouritesOnly = false; //todo save this in preferences
    private AlarmHelper alarmHelper;
    private Snackbar genreSnackbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Forced landscape as the view doesn't work well in portrait =(
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.week_fragment, container, false);

        alarmHelper = new AlarmHelper(getActivity(), rootView);

        setupWeekView();
        setupDateTimeInterpreter(true);

        return rootView;
    }

    public void toggleFavourites() {
        favouritesOnly = !favouritesOnly;
        mWeekView.toggleFavourites(favouritesOnly);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    private void setupWeekView() {
        mWeekView = (WeekView) rootView.findViewById(R.id.weekView);

        mWeekView.setOnEventClickListener(this);
        mWeekView.setEventLongPressListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

      //  mWeekView.setMinDate(Calendar.getInstance()); These are breaking the stop fling on touch somehow, so for now commenting out, and disabling the left/right gestures in WeekView
      //  mWeekView.setMaxDate(Calendar.getInstance()); These are breaking the stop fling on touch somehow, so for now commenting out, and disabling the left/right gestures in WeekView
        mWeekView.setStages(7);

        mWeekView.setNumberOfVisibleDays(7);


        mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
        mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics()));
        mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics()));
    }

    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     *
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour, int minutes) {
                String strMinutes = String.format("%02d", minutes);
                if (hour > 11) {
                    if (hour == 12) {
                        return "12:" + strMinutes + " PM";
                    } else {
                        return (hour - 12) + ":" + strMinutes + " PM";
                    }
                } else {
                    if (hour == 0) {
                        return "12:" + strMinutes + " AM";
                    } else {
                        return hour + ":" + strMinutes + " AM";
                    }
                }
            }
        });
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {

        if (genreSnackbar != null && genreSnackbar.isShown()) {
            genreSnackbar.dismiss();
        }

        final View coordinatorLayoutView = rootView.findViewById(R.id.snackbarPosition);
        coordinatorLayoutView.setVisibility(View.VISIBLE);

        genreSnackbar = Snackbar.make(coordinatorLayoutView, event.getArtist().getArtistName() + ": " + event.getArtist().getGenres().replace(",", ", "), Snackbar.LENGTH_LONG)
                .setDuration(Snackbar.LENGTH_LONG);

        View snackbarView = genreSnackbar.getView();

        snackbarView.setBackgroundColor(ContextCompat.getColor(getActivity(), (ColorUtil.getStageColors()[event.getArtist().getStage()])));

        TextView snackBarTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        TextView snackBarActionTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_action);

        snackBarTextView.setTextColor(Color.WHITE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            snackBarTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        snackBarActionTextView.setTextColor(Color.WHITE);

        snackBarActionTextView.setTextSize(14);


        genreSnackbar.show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        if (event.getArtist().isFavorite()) {
            event.getArtist().setFavorite(false);
            alarmHelper.dismissSnackbar();
            alarmHelper.cancelAlarm(event.getArtist());
        } else {
            alarmHelper.showSetAlarmSnackBar(event.getArtist());
            event.getArtist().setFavorite(true);
        }

        event.getArtist().save();
        MainActivity.shambhala.updateArtistById(event.getArtist().getId());

        mWeekView.invalidate();
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        return null;
    }

    @Override
    public List<? extends WeekViewEvent> onLoadStage(int stage) {
        List<WeekViewEvent> currentPeriodEvents = new ArrayList<WeekViewEvent>();

        ArrayList<Artist> artists = MainActivity.shambhala.getArtistsByDayAndStage(MainActivity.currentDay, stage);

        int x = 0;

        for (Artist artist : artists) {


            DateTime startTime = DateUtils.getFullDateTimeForArtist(artist);
            DateTime endTime = DateUtils.getFullDateEndTimeForArtist(artist);

            //Rich-E-Rich goes past the apps festival end time of 11am (24h per day) so exception needs to be made for it.
            //or any other artist who goes past 11am.
            if (endTime.isAfter(new DateTime(2016, 8, 8, 11, 59, 59, 99).withZone(Constants.timeZone))) {
                endTime = new DateTime(2016, 8, 8, 11, 0, 0, 0).withZone(Constants.timeZone);
            }

            WeekViewEvent event = new WeekViewEvent(1, artist.getAristName(), startTime.toGregorianCalendar(), endTime.toGregorianCalendar());
            event.setArtist(artist);
            event.setStage(stage);

            event.setFavourite(artist.isFavorite());

            int color = ContextCompat.getColor(getContext(), ColorUtil.getStageColors()[stage]);

            if (!event.isFavourite()) {
                color = ColorUtil.adjustAlpha(color, 0.58f);
            }

            event.setColor(color);
            currentPeriodEvents.add(event);
        }

        return currentPeriodEvents;
    }

    public void onEventMainThread(ChangeDateEvent event) {
        mWeekView.invalidate();
    }

    public void onEventMainThread(SearchSelectedEvent event) {
        double hourPosition = (event.getArtist().getStartPosition() / 2) - 0.5;
        mWeekView.goToHour(hourPosition < 0 ? 0 : hourPosition);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}