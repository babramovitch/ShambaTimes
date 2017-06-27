package com.shambatimes.schedule;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.shambatimes.Alarms.AlarmHelper;
import com.shambatimes.schedule.Settings.SettingsActivity;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.Util.DateUtils;
import com.shambatimes.schedule.Util.Util;
import com.shambatimes.schedule.events.ChangeDateEvent;
import com.shambatimes.schedule.events.DataChangedEvent;
import com.shambatimes.schedule.events.SearchSelectedEvent;
import com.shambatimes.schedule.events.ToggleToStageEvent;
import com.shambatimes.schedule.events.ToggleToTimeEvent;
import com.shambatimes.schedule.myapplication.R;
import com.shambatimes.weekview.DateTimeInterpreter;
import com.shambatimes.weekview.MonthLoader;
import com.shambatimes.weekview.WeekView;
import com.shambatimes.weekview.WeekViewEvent;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.greenrobot.event.EventBus;

public class WeekScheduleFragment extends Fragment implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener {
    private static final String TAG = "WeekSchedule";
    private static final String FAVORITES_ONLY = "favoritesOnly";
    private static final String NOW_ONLY = "nowOnly";
    private static final String VISIBLE_HOUR = "visibleHour";

    private View rootView;
    private WeekView mWeekView;
    private AlarmHelper alarmHelper;
    private Snackbar genreSnackbar;
    private Artist snackbarArtist;

    private boolean favouritesOnly = false;
    private boolean showOnlyNow = false;
    private int currentDate = 0;

    AlertDialog alertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.week_fragment, container, false);
        alarmHelper = new AlarmHelper(getActivity(), rootView);


        setupWeekView();
        setupDateTimeInterpreter(true);

        if (savedInstanceState != null) {
            favouritesOnly = savedInstanceState.getBoolean(FAVORITES_ONLY, false);
            showOnlyNow = savedInstanceState.getBoolean(NOW_ONLY, false);

            double visibleHour = savedInstanceState.getDouble(VISIBLE_HOUR, 0);

            if (isFavoritesOnly()) {
                mWeekView.toggleFavourites(true);
            }

            if (isShowOnlyNow()) {
                mWeekView.toggleNow(true);
            }

            if (visibleHour != 0) {
                gotoHour(visibleHour + 1.5);
            }

        } else {
            Bundle bundle = getArguments();

            if (bundle != null) {
                if (bundle.getInt("TIME", -1) != -1) {
                    gotoHour((bundle.getInt("TIME", -1) / 2));
                }
            }
        }

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if (!prefs.getBoolean("instructionsSeen", false)) {
            alertDialog = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle).create();
            alertDialog.setMessage(Html.fromHtml("Here's some quick info about the new grid schedule so you can get the most out of it<br><br>" +
                    "1. You can zoom in and out with the 2 finger pinch gesture<br><br>" +
                    "2. You can see all the stages at once in landscape<br><br>" +
                    "3. Long pressing on an artist will mark it as favorite<br><br>" +
                    "4. Pressing on the heart above will show only your favorites<br><br>" +
                    "5. Pressing on an artist will present a JUMP button. You can <b>press</b> or <b>long press</b> this button to jump to the artist on the Time or Stage schedules<br><br>" +
                    "6. Long pressing an artist on the Time or Stage schedules will take you here"));

            alertDialog.setCancelable(false);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Got it!",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            prefs.edit().putBoolean("instructionsSeen", true).apply();
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

        return rootView;
    }

    public void setShowOnlyNow(boolean showOnlyNow) {
        this.showOnlyNow = showOnlyNow;
    }

    public boolean isShowOnlyNow() {
        return showOnlyNow;
    }

    public void gotoHour(double hourPosition) {
        Log.i("TAG", "Go to hour: " + hourPosition);
        mWeekView.goToHour(hourPosition < 1.5 ? 0 : hourPosition - 1.5);
        favouritesOnly = false;
        mWeekView.toggleNow(showOnlyNow);
    }

    public void toggleFavourites() {
        favouritesOnly = !favouritesOnly;
        showOnlyNow = false;
        mWeekView.toggleFavourites(favouritesOnly);
    }

    public void toggleNow() {
        favouritesOnly = false;
        showOnlyNow = !showOnlyNow;
        mWeekView.toggleNow(showOnlyNow);
    }

    public boolean isFavoritesOnly() {
        return favouritesOnly;
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

        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mWeekView.setNumberOfVisibleDays(7);
            mWeekView.setScheduleScrollingEnabled(false);
        } else {
            mWeekView.setNumberOfVisibleDays(4);
            mWeekView.setScheduleScrollingEnabled(true);
        }

        mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
        mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics()));
        mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics()));

        float startingHourHeight = getResources().getDimension(R.dimen.hour_height);

        Log.i("TAG", "Starting hour: " + startingHourHeight);
        mWeekView.setHourHeight((int) startingHourHeight);

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
    public void onEventClick(final WeekViewEvent event, RectF eventRect) {

        if (genreSnackbar != null && genreSnackbar.isShown()) {
            genreSnackbar.dismiss();
        }

        snackbarArtist = event.getArtist();

        final View coordinatorLayoutView = rootView.findViewById(R.id.snackbarPosition);
        coordinatorLayoutView.setVisibility(View.VISIBLE);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String format = preferences.getString(SettingsActivity.TIME_FORMAT, "24");
        DateTimeFormatter dateStringFormat = DateUtils.getTimeFormat(format);

        genreSnackbar = Snackbar.make(coordinatorLayoutView, event.getArtist().getArtistName()
                + " - " + DateUtils.formatTime(dateStringFormat, snackbarArtist.getStartTimeString())
                + " to " + DateUtils.formatTime(dateStringFormat, snackbarArtist.getEndTimeString())
                + " : " + snackbarArtist.getGenres().replace(",", ", "), Snackbar.LENGTH_LONG)
                .setAction(R.string.jump, snackBarClickListener)
                .setDuration(Snackbar.LENGTH_LONG);

        View snackbarView = genreSnackbar.getView();

        AlarmHelper alarmHelper = new AlarmHelper(getContext(), null); //TODO fix this from color util / helper
        snackbarView.setBackgroundColor(alarmHelper.getSnackBarColor(event.getArtist().getStage()));

        TextView snackBarTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        snackBarTextView.setTextColor(ColorUtil.snackbarTextColor(getActivity()));

        TextView snackBarActionTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_action);
        if (ColorUtil.nightMode) {
            snackBarActionTextView.setTextColor(ContextCompat.getColor(getActivity(), ColorUtil.getStageColors()[snackbarArtist.getStage()]));
        } else {
            snackBarActionTextView.setTextColor(Color.WHITE);
        }

        snackBarActionTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                EventBus.getDefault().post(new ToggleToStageEvent(event.getArtist().getStage(), event.getArtist().getArtistName()));
                return false;
            }
        });

        snackBarActionTextView.setTextSize(14);

        genreSnackbar.show();
    }

    final View.OnClickListener snackBarClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            EventBus.getDefault().post(new ToggleToTimeEvent(snackbarArtist.getStartPosition()));
        }
    };

    @Override
    public void onEventLongPress(final WeekViewEvent event, RectF eventRect) {
        if (event.getArtist().isFavorite()) {
            event.getArtist().setFavorite(false);
            event.getArtist().setIsAlarmSet(false);
            alarmHelper.dismissSnackbar();
            alarmHelper.cancelAlarm(event.getArtist());
        } else {

            alarmHelper.setOnAlarmStateChangedListener(new AlarmHelper.OnAlarmStateChangedListener() {
                @Override
                public void alarmStateChanged() {
                    if (event.getArtist().isAlarmSet()) {
                        MainActivity.shambhala.updateArtistById(event.getArtist().getId());
                    }
                }
            });

            alarmHelper.showSetAlarmSnackBar(event.getArtist());
            event.getArtist().setFavorite(true);
        }

        event.getArtist().save();
        MainActivity.shambhala.updateArtistById(event.getArtist().getId());

        EventBus.getDefault().postSticky(new DataChangedEvent(true, event.getArtist().getId()));

        mWeekView.invalidate();
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        return null;
    }

    @Override
    public List<? extends WeekViewEvent> onLoadStage(int stage) {
        List<WeekViewEvent> currentPeriodEvents = new ArrayList<WeekViewEvent>();

        ArrayList<Artist> artists = MainActivity.shambhala.getArtistsByDayAndStage(currentDate, stage);

        for (Artist artist : artists) {

            DateTime startTime = DateUtils.getFullDateTimeForArtist(artist);
            DateTime endTime = DateUtils.getFullDateEndTimeForArtist(artist);

            //Rich-E-Rich goes past the apps festival end time of 11am (24h per day) so exception needs to be made for it.
            //or any other artist who goes past 11am.

            if (endTime.isAfter(DateUtils.getEndOfFestivalGridDate(getActivity()))) {
                endTime = DateUtils.getEndOfFestivalGridDate(getActivity());
            }

            WeekViewEvent event = new WeekViewEvent(1, artist.getAristName(), startTime.toGregorianCalendar(), endTime.toGregorianCalendar());
            event.setArtist(artist);
            event.setStage(stage);

            event.setFavourite(artist.isFavorite());

            int color = ContextCompat.getColor(getContext(), ColorUtil.getStageColors()[stage]);

            if (!event.isFavourite()) {
                color = fadeNonFavouriteColor(color);
            }

            event.setColor(color);
            currentPeriodEvents.add(event);
        }

        return currentPeriodEvents;
    }

    private int fadeNonFavouriteColor(int color) {
        float[] hsvColor = new float[3];
        Color.colorToHSV(color, hsvColor);

        hsvColor[1] = hsvColor[1] - (hsvColor[1] * 0.35f);
        hsvColor[2] = hsvColor[2] + 0.05f;
        color = Color.HSVToColor(hsvColor);
        return color;
    }

    public void onEventMainThread(ChangeDateEvent event) {
        currentDate = event.getPosition();

        //Hack because thursday starts with AMP off screen, and given how the database is set up,
        //it's a lot of work to re-order the stages given past data.
        if (currentDate == 0) {
            mWeekView.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                            float offset = mWeekView.getWidthPerStage() * 2f * -1;
                            Log.i("SCROLL", "OFFSET: " + offset);
                            mWeekView.setXOriginOffset(offset);
                        }
                    } catch (Exception e) {
                        //Something went wrong, but don't crash the app over this failing
                        Crashlytics.logException(e);
                    }
                }
            });
        }

        mWeekView.invalidate();
    }

    public void onEventMainThread(SearchSelectedEvent event) {
        double hourPosition = (event.getArtist().getStartPosition() / 2) - 0.5;
        gotoHour(hourPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putBoolean(FAVORITES_ONLY, favouritesOnly);
        savedInstanceState.putBoolean(NOW_ONLY, showOnlyNow);

        if (mWeekView != null) {
            savedInstanceState.putDouble(VISIBLE_HOUR, mWeekView.getFirstVisibleHour());
        }


        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {

        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }

        super.onDestroy();

    }
}