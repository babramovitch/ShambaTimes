package com.shambatimes.Alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.shambatimes.schedule.Artist;
import com.shambatimes.schedule.Settings.SettingsActivity;
import com.shambatimes.schedule.Shambhala;
import com.shambatimes.schedule.Util.ColorUtil;
import com.shambatimes.schedule.Util.DateUtils;
import com.shambatimes.schedule.myapplication.R;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by babramovitch on 11/24/2015.
 */
public class AlarmHelper {

    public interface OnAlarmStateChangedListener {
        void alarmStateChanged();
    }

    // TODO - REVIEW THIS http://pguardiola.com/blog/darealfragmentation-alarms/
    // TODO - REVIEW THIS https://github.com/evernote/android-job

    private Context context;
    private Artist artist;
    private View layout;
    private Snackbar snackbar;
    private OnAlarmStateChangedListener onAlarmStateChangedListener;

    private int[] stageColors = ColorUtil.getStageColors();

    public AlarmHelper(Context context, View layout) {
        this.context = context;
        this.layout = layout;
    }

    public int getSnackBarColor(int stage) {
        if (ColorUtil.nightMode) {
            return ContextCompat.getColor(context, R.color.lighterSecondaryUISelectionGray);
        } else {
            return ContextCompat.getColor(context, stageColors[stage]);
        }
    }

    public void setOnAlarmStateChangedListener(OnAlarmStateChangedListener listener) {
        onAlarmStateChangedListener = listener;
    }

    public void showSetAlarmSnackBar(Artist artist) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String alarmMinutes = preferences.getString(SettingsActivity.ALARM_TIMES, "30");

        //if (DateUtils.getFullDateTimeForArtist(artist).minus(60000 * Integer.valueOf(alarmMinutes)).isAfter(System.currentTimeMillis())) {
            if (Shambhala.getFestivalYear(context).equals(Shambhala.CURRENT_YEAR)) {

                this.artist = artist;

                final View coordinatorLayoutView = layout.findViewById(R.id.snackbarPosition);
                coordinatorLayoutView.setVisibility(View.VISIBLE);

                snackbar = Snackbar.make(coordinatorLayoutView, "Add alarm " + alarmMinutes + " minutes before " + artist.getAristName() + "?", Snackbar.LENGTH_LONG)
                        .setAction("OK", snackBarClickListener)
                        .setDuration(Snackbar.LENGTH_LONG);

                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(getSnackBarColor(artist.getStage()));

                TextView snackBarTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                TextView snackBarActionTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_action);

                snackBarTextView.setTextColor(ColorUtil.snackbarTextColor(context));
                snackBarActionTextView.setTextColor(ColorUtil.snackbarTextColor(context));

                snackBarActionTextView.setTextSize(14);

                snackbar.show();
            }
      //  }
    }

    final View.OnClickListener snackBarClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            setAlarm(artist);
        }
    };

    public void setAlarm(Artist artist) {

        if (Shambhala.getFestivalYear(context).equals(Shambhala.CURRENT_YEAR)) {

            this.artist = artist;

            Log.i("AlarmHelper", "Alarm set for " + artist.getArtistName());

            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            alarmIntent.putExtra("name", artist.getArtistName());
            alarmIntent.putExtra("id", artist.getId().toString());

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, artist.getId().intValue(), alarmIntent, 0);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            artist.setIsAlarmSet(true);
            artist.save();

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            int alarmMinutes = Integer.valueOf(preferences.getString(SettingsActivity.ALARM_TIMES, "30"));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, getAlarmTime(artist, alarmMinutes), pendingIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                manager.setExact(AlarmManager.RTC_WAKEUP, getAlarmTime(artist, alarmMinutes), pendingIntent);
            } else {
                manager.set(AlarmManager.RTC_WAKEUP, getAlarmTime(artist, alarmMinutes), pendingIntent);
            }

            if (onAlarmStateChangedListener != null) {
                onAlarmStateChangedListener.alarmStateChanged();
            }
        }
    }

    public void showCancelAlarmSnackbar(Artist artist) {
        if (Shambhala.getFestivalYear(context).equals(Shambhala.CURRENT_YEAR)) {

            this.artist = artist;

            final View coordinatorLayoutView = layout.findViewById(R.id.snackbarPosition);
            coordinatorLayoutView.setVisibility(View.VISIBLE);

            snackbar = Snackbar.make(coordinatorLayoutView, "Remove alarm for " + artist.getAristName() + "?", Snackbar.LENGTH_LONG)
                    .setAction("OK", snackBarCancelClickListener)
                    .setDuration(Snackbar.LENGTH_LONG);

            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(getSnackBarColor(artist.getStage()));

            TextView snackBarTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            TextView snackBarActionTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_action);

            snackBarTextView.setTextColor(ColorUtil.snackbarTextColor(context));
            snackBarActionTextView.setTextColor(ColorUtil.snackbarTextColor(context));

            snackBarActionTextView.setTextSize(14);

            snackbar.show();
        }
    }

    final View.OnClickListener snackBarCancelClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            artist.setIsAlarmSet(false);
            artist.save();
            cancelAlarm(artist);
        }
    };

    public void cancelAlarm(Artist artist) {
        if (Shambhala.getFestivalYear(context).equals(Shambhala.CURRENT_YEAR)) {

            Log.i("AlarmHelper", "Alarm cancelled for " + artist.getArtistName());

            Intent cancelAlarmIntent = new Intent(context, AlarmReceiver.class);
            cancelAlarmIntent.putExtra("name", artist.getArtistName());
            cancelAlarmIntent.putExtra("id", artist.getId().toString());

            PendingIntent.getBroadcast(context, artist.getId().intValue(), cancelAlarmIntent, PendingIntent.FLAG_UPDATE_CURRENT).cancel();

            if (onAlarmStateChangedListener != null) {
                onAlarmStateChangedListener.alarmStateChanged();
            }
        }
    }

    public void dismissSnackbar() {
        if (snackbar != null) {
            Log.i("AlarmHelper", "Snackbar dismissed");
            snackbar.dismiss();
        }
    }

    public static void recalculateAllAlarmTimes(Context context) {
        recalculateAllAlarmTimes(context, 0);
    }


    public static void recalculateAllAlarmTimes(Context context, int alarmMinutes) {
        try {

            String[] query1 = {"1", Shambhala.CURRENT_YEAR};
            ArrayList<Artist> artists = (ArrayList<Artist>) Artist.find(Artist.class, "is_Alarm_Set = ? and year = ?", query1, null, null, null);

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (alarmMinutes == 0) {
                alarmMinutes = Integer.valueOf(preferences.getString(SettingsActivity.ALARM_TIMES, "30"));
            }

            for (Artist artist : artists) {

                Intent alarmIntent = new Intent(context, AlarmReceiver.class);
                alarmIntent.putExtra("name", artist.getArtistName());
                alarmIntent.putExtra("id", artist.getId().toString());

                PendingIntent.getBroadcast(context, artist.getId().intValue(), alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT).cancel();
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, artist.getId().intValue(), alarmIntent, 0);

                AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, getAlarmTime(artist, alarmMinutes), pendingIntent);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    manager.setExact(AlarmManager.RTC_WAKEUP, getAlarmTime(artist, alarmMinutes), pendingIntent);
                } else {
                    manager.set(AlarmManager.RTC_WAKEUP, getAlarmTime(artist, alarmMinutes), pendingIntent);
                }

            }
            Log.i("AlarmHelper", "There are " + artists.size() + " saved alarms");
        } catch (Exception e) {
            Log.e("AlarmHelper", "BOOM", e);
        }
    }

    private static long getAlarmTime(Artist artist, int minutes) {
        DateTime startTime = DateUtils.getFullDateTimeForArtist(artist);

        DateTime alarmTime = startTime.minus(60000 * minutes);
        Log.i("AlarmHelper", "Artist Time:" + startTime.toString());
        Log.i("AlarmHelper", "Alarm  Time: " + alarmTime.toString());

        DateTime now = new DateTime();

        return now.plusMillis(30000).getMillis(); //alarmTime.getMillis();
    }

    public static void cancelAlarmIntent(Context context, Artist artist) {
        try {
            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            alarmIntent.putExtra("name", artist.getArtistName());
            alarmIntent.putExtra("id", artist.getId().toString());

            PendingIntent.getBroadcast(context, artist.getId().intValue(), alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT).cancel();
        } catch (Exception e) {
            Log.e("AlarmHelper", "Exception clearing alarm", e);
        }
    }
}
